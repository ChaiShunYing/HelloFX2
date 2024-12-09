package Chapter4;

class PrintChar implements Runnable {
    private char charToPrint;
    private int times;

    public PrintChar(char charToPrint, int times) {
        this.charToPrint = charToPrint;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint);  // Print character
        }
    }
}

class PrintDigit implements Runnable {
    private int digitToPrint;
    private int times;

    public PrintDigit(int digitToPrint, int times) {
        this.digitToPrint = digitToPrint;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(digitToPrint);  // Print digit
        }
    }
}

public class withRunnable {
    public static void main(String[] args) {
        // Correct: You can switch between different tasks easily
        Runnable printA = new PrintChar('a', 100);
        Thread threadA = new Thread(printA);
        threadA.run();
        
        Runnable print1 = new PrintDigit(1, 100);
        Thread thread1 = new Thread(print1);
        thread1.run();
    }
}