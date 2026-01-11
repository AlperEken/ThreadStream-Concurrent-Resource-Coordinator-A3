package model;

import controller.Controller;
import view.PanelConsumers;
import view.PanelProgress;
import model.Product;
import model.Storage;



public class Consumer implements Runnable {
    private final String name;
    private final int maxCapacity;
    private final int typeIndex;
    private volatile boolean running = true;
    private final Storage storage;
    private final PanelConsumers panelConsumers;
    private final PanelProgress panelProgress;
    private final Controller controller;

    private final Product[] internalStorage;
    private int itemCount = 0;

    public Consumer(String name, int maxCapacity, int typeIndex,
                    Storage storage, PanelConsumers panelConsumers, PanelProgress panelProgress, Controller controller) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.typeIndex = typeIndex;
        this.storage = storage;
        this.panelConsumers = panelConsumers;
        this.panelProgress = panelProgress;
        this.internalStorage = new Product[maxCapacity];
        this.controller = controller;
    }

    public void stopRunning() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                panelConsumers.updateConsumerStatus("Waiting", name);

                Product product = storage.consume();
                panelConsumers.updateConsumerStatus("Consuming", name);

                if (itemCount >= maxCapacity) {
                    panelConsumers.updateConsumerStatus("Truck full", name);

                    if (panelConsumers.getContinueLoading(name)) {
                        Thread.sleep(2000);
                        itemCount = 0;
                        panelConsumers.updateConInfo(typeIndex, itemCount);
                        panelConsumers.updateConsumerList("--- Truck emptied ---", name);
                    } else {
                        while (running && !panelConsumers.getContinueLoading(name)) {
                            panelConsumers.updateConsumerStatus("Waiting to unload", name);
                            Thread.sleep(500);
                        }

                        if (!running) return;

                        itemCount = 0;
                        panelConsumers.updateConInfo(typeIndex, itemCount);
                        panelConsumers.updateConsumerList("--- Truck emptied ---", name);
                    }
                }

                internalStorage[itemCount++] = product;
                panelConsumers.updateConsumerList(product.toString(), name);
                panelConsumers.updateConInfo(typeIndex, itemCount);

                Thread.sleep(500);

            } catch (InterruptedException e) {
                panelConsumers.updateConsumerStatus("Stopped (Interrupted)", name);
                return;
            }
        }

        panelConsumers.updateConsumerStatus("Stopped", name);
    }




}
