package model;

import controller.Controller;
import java.util.concurrent.Semaphore;

public class Storage {
    private final int bufferSize = 30;
    private final Product[] buffer = new Product[bufferSize];
    private int in = 0;
    private int out = 0;
    private final Controller controller;
    private int count = 0;

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore empty = new Semaphore(bufferSize);
    private final Semaphore full = new Semaphore(0);

    public Storage(Controller controller) {
        this.controller = controller;
    }

    public void produce(Product item) throws InterruptedException {

        System.out.println("Innan produktion, storlek: " + currentBufferSize());
        empty.acquire();
        mutex.acquire(); 


        buffer[in] = item;
        System.out.println("PRODUCERADE på plats " + in);
        in = (in + 1) % bufferSize;
        count++;
        System.out.println("in ökar till: " + in);


        int size = (in >= out) ? (in - out) : (bufferSize - out + in);
        controller.updateProgressBar(size);


        mutex.release();
        full.release();


    }


    public Product consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();

        System.out.println("KONSUMERAR från plats " + out);
        Product item = buffer[out];
        out = (out + 1) % bufferSize;
        count--;
        System.out.println(" out ökar till: " + out);


        mutex.release();
        empty.release();
        controller.updateProgressBar();

        return item;
    }

    public int currentBufferSize() {
        int size;
        try {
            mutex.acquire();
            size = count;
        } catch (InterruptedException e) {
            return -1;
        } finally {
            mutex.release();
        }
        return size;
    }

}
