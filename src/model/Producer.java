package model;

import controller.Controller;
import model.Product;
import model.Storage;
import view.PanelProducers;

import java.util.Random;

public class Producer implements Runnable {
    private final String name;
    private final Storage storage;
    private final PanelProducers panelProducers;
    private volatile boolean running = true;
    private final Controller controller;
    private final Random rand = new Random();

    private final String[] productNames = {
            "Apple", "Laptop", "Cheese", "TV", "Bread", "Hammer", "Phone", "Milk", "Wrench", "Screwdriver"
    };
    private final double[] productPrices = {10, 999, 25, 7999, 18, 90, 5999, 22, 55, 75};
    private final Product.CategoryType[] categories = {
            Product.CategoryType.Food, Product.CategoryType.Electrics, Product.CategoryType.Food,
            Product.CategoryType.Electrics, Product.CategoryType.Food, Product.CategoryType.Tools,
            Product.CategoryType.Electrics, Product.CategoryType.Food, Product.CategoryType.Tools, Product.CategoryType.Tools
    };



    public Producer(String name, Storage storage, PanelProducers panelProducers, Controller controller) {
        this.name = name;
        this.storage = storage;
        this.panelProducers = panelProducers;
        this.controller = controller;
    }

    public void stopRunning() {
        running = false;
    }
    @Override
    public void run() {
        while (running) {
            try {
                panelProducers.updateProducerStatus("Waiting", name);
                Thread.sleep(rand.nextInt(700) + 300);

                int index = rand.nextInt(productNames.length);
                Product product = new Product(productNames[index], productPrices[index], categories[index]);

                panelProducers.updateProducerStatus("Producing", name);
                storage.produce(product);

                controller.updateProgressBar();
                panelProducers.updateProductList(product.toString(), name);

                Thread.sleep(150);

            } catch (InterruptedException e) {
                panelProducers.updateProducerStatus("Stopped (Interrupted)", name);
                return;
            }
        }

        panelProducers.updateProducerStatus("Stopped", name);
    }


}
