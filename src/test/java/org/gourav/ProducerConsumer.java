package org.gourav;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {


    public static void main(String[] args) {
        //withBlockingQueue();
        withBlockingQueueManually();
    }

    private static void withBlockingQueue() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue(1);

        Runnable producer = () -> {
            int value = 0;
            while (true) {
                try {
                    blockingQueue.put(value);
                    System.out.println("Produced=" + value);
                    value++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            int value = 0;
            while (true) {
                try {
                    value = blockingQueue.take();
                    System.out.println("Consumed=" + value);

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(producer);
        executorService.execute(consumer);
        executorService.shutdown();
    }

     private static void withBlockingQueueManually() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(1);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable producer = () -> {
            try {
                int value = 0;
                while (true) {
                    blockingQueue.put(value);
                    System.out.println(new java.util.Date() + " Produced=" + value);
                    value++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                int value = 0;
                while (true) {
                    value = blockingQueue.take();
                    System.out.println(new java.util.Date() + " Consume=" + value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        };
        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.shutdown();
    }


}
