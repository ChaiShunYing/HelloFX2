package LAB_Practice;

import java.util.concurrent.locks.*;

class SharedResource {
    private boolean isAvailable = false;
    private final Lock lock = new ReentrantLock();
    private final Condition produceCondition = lock.newCondition();
    private final Condition consumeCondition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while (isAvailable) { // Condition to wait
                produceCondition.await(); // Wait for the consume signal
            }
            System.out.println("Produced an item");
            isAvailable = true;
            consumeCondition.signal(); // Notify waiting consumers
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (!isAvailable) { // Condition to wait
                consumeCondition.await(); // Wait for the produce signal
            }
            System.out.println("Consumed an item");
            isAvailable = false;
            produceCondition.signal(); // Notify waiting producers
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}

public class _4LockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) resource.produce();
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) resource.consume();
        });

        producer.start();
        consumer.start();
    }
}
