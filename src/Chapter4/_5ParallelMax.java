package Chapter4;

import java.util.concurrent.*;

public class _5ParallelMax {
    public static void main(String[] args) {
        // Create a list
        final int N = 900;
        int[] list = new int[N];
        for (int i = 0; i < list.length; i++) {
            list[i] = i;
        }

        long startTime = System.currentTimeMillis();
        System.out.println("\nThe maximal number is " + max(list));
        long endTime = System.currentTimeMillis();
        System.out.println("The number of processors is " +
                Runtime.getRuntime().availableProcessors());
        System.out.println("Time is " + (endTime - startTime) + " milliseconds");
    }

    public static int max(int[] list) {
        RecursiveTask<Integer> task = new MaxTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class MaxTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = 100;
        private int[] list;
        private int low;
        private int high;

        public MaxTask(int[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }
int j = 0;
        @Override
        public Integer compute() {
            if (high - low < THRESHOLD) {
                int max = list[low];
                for (int i = low; i < high; i++) {
                    if (list[i] > max) {
                        max = list[i];
                    }
                }
                return max;
            } else {
                int mid = (low + high) / 2;
                System.out.println((j + 1) + ". " + high + " " + low + " " + mid);
                RecursiveTask<Integer> left = new MaxTask(list, low, mid);
                RecursiveTask<Integer> right = new MaxTask(list, mid, high);

                right.fork();
                left.fork();
                j++;
                return Math.max(left.join(), right.join());
             
            }
        }
    }
}
