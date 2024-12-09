package Chapter4;

class SharedResource {
    private boolean isAvailable = false;

    public synchronized void produce() {
        while (isAvailable) { // Condition to wait
            try {
                wait(); // Release the lock and wait
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Produced an item");
        isAvailable = true;
        notify(); // Wake up one waiting thread
    }

    public synchronized void consume() {
        while (!isAvailable) { // Condition to wait
            try {
                wait(); // Release the lock and wait
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed an item");
        isAvailable = false;
        notify(); // Wake up one waiting thread
    }
}

public class _4SynchronizedExample {
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
