package controller;
import model.*;
import model.Consumer;
import model.Storage;
import view.*;
import model.Producer;


import java.nio.Buffer;
import java.util.ArrayList;

public class Controller {
    private Consumer[] consumers = new Consumer[3];
    private Consumer[] consumerBackup = new Consumer[3];
    private Thread[] producerThreads = new Thread[3];
    private Thread[] consumerThreads = new Thread[3];
    private Producer[] producers = new Producer[3];
    private Buffer buffer;
    private View view;
    private Storage storage;
    private final int maxBufferSize = 30;
    private final String[] producerNames = {"ICA", "Coop", "City Gross"};
    private final String[] consumerNames = {"ICA", "Coop", "City Gross"};
    private final int[] consumerCapacities = {12, 15, 10};




    public Controller() {
        setup();
    }

    private void setup() {
        storage = new Storage(this);

        view = new View(this);
        view.Start();


        PanelProducers panelProducers = view.getPanelProducers();
        PanelConsumers panelConsumers = view.getPanelConsumers();
        PanelProgress panelProgress = view.getPanelProgress();
    }


    public void startProducer(int index) {
        producers[index] = new Producer(producerNames[index], storage, view.getPanelProducers(), this);
        producerThreads[index] = new Thread(producers[index]);
        producerThreads[index].start();

    }

    public void startConsumer(int index) {

        consumers[index] = new Consumer(
                consumerNames[index],
                consumerCapacities[index],
                index,
                storage,
                view.getPanelConsumers(),
                view.getPanelProgress(), this
        );

        consumerThreads[index] = new Thread(consumers[index]);
        consumerThreads[index].start();
    }

    public void stopProducer(int index) {
        if (producers[index] != null) {
            producers[index].stopRunning();
            view.getPanelProducers().enableStartButton(producerNames[index]);
        }
    }

    public void stopConsumer(int index) {
        if (consumers[index] != null) {
            consumers[index].stopRunning();
        }
    }


    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public synchronized void updateProgressBar() {
        view.updateProgressBar(storage.currentBufferSize());
    }


    public void updateProgressBar(int size) {
        view.updateProgressBar(size);

    }







}
