package LAB4;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class java_answer {
    public static void main(String[] args) {
        int size = 2000;

        // Generate two random matrices
        double[][] matrixA = generateMatrix(size, size);
        double[][] matrixB = generateMatrix(size, size);
        double[][] result;

        // Sequential multiplication
        long startTime = System.currentTimeMillis();
        result = sequentialMultiplyMatrix(matrixA, matrixB);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential time: " + (endTime - startTime) + " ms");

        // Parallel multiplication
        startTime = System.currentTimeMillis();
        result = parallelMultiplyMatrix(matrixA, matrixB);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel time: " + (endTime - startTime) + " ms");
    }

    // Generate random matrix
    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random();
            }
        }
        return matrix;
    }

    // Sequential matrix multiplication
    public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int commonDim = b.length;

        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < commonDim; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // Parallel matrix multiplication using ForkJoinPool
    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        double[][] result = new double[rows][cols];

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MatrixMultiplicationTask(a, b, result, 0, rows));
        pool.shutdown();

        return result;
    }

    // RecursiveTask for matrix multiplication
    private static class MatrixMultiplicationTask extends RecursiveTask<Void> {
        private static final int THRESHOLD = 100; // Task size for splitting
        private double[][] a, b, result;
        private int startRow, endRow;

        public MatrixMultiplicationTask(double[][] a, double[][] b, double[][] result, int startRow, int endRow) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        protected Void compute() {
            int numRows = endRow - startRow;

            // Process sequentially if the task is small
            if (numRows <= THRESHOLD) {
                multiplyPortion(a, b, result, startRow, endRow);
            } else {
                // Split the task
                int midRow = (startRow + endRow) / 2;

                MatrixMultiplicationTask upperTask = new MatrixMultiplicationTask(a, b, result, startRow, midRow);
                MatrixMultiplicationTask lowerTask = new MatrixMultiplicationTask(a, b, result, midRow, endRow);

                // Execute subtasks in parallel
                invokeAll(upperTask, lowerTask);
            }
            return null;
        }

        // Sequential multiplication for a portion of rows
        private void multiplyPortion(double[][] a, double[][] b, double[][] result, int startRow, int endRow) {
            int cols = b[0].length;
            int commonDim = b.length;

            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int k = 0; k < commonDim; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
    }
}