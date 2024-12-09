package LAB4;
//Name: Chai Shun Ying
//Matric: 224414
//Lab information: Lab 4 Ques 3
//Date: 6/12/2024
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ques3 extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		final int SIZE = 2000;
		
		double [][] matrix1 = new double[SIZE][SIZE]; // Set the size of matrix1 (2000 x 2000)
		// Set the random numbers in matrix1 (1-10)
		for(int row = 0; row < matrix1.length; row++) 
		{
			for(int column = 0; column < matrix1[0].length; column++)
			{
				matrix1[row][column] = (Math.random() * (11 - 1)) + 1; 
			}
		}
		
		double [][] matrix2 = new double[SIZE][SIZE]; // Set the size of matrix2 (2000 x 20000)
		// Set the random numbers in matrix2 (1-10)
		for(int row = 0; row < matrix2.length; row++)
		{
			for(int column = 0; column < matrix2[0].length; column++)
			{
				matrix2[row][column] = (Math.random() * (11 - 1)) + 1;
			}
		}
		
		// Create the array to store of matrix using parallel and sequential method
		double [][] resultMatrixP = new double [SIZE][SIZE];
		double [][] resultMatrixS = new double [SIZE][SIZE];
		
		// Create ListView to hold content of matrix1, matrix2, multiplication result of 2 matrix that run in parallel and run in sequence
		ListView<String> matrix1ListView = new ListView<>();
		ListView<String> matrix2ListView = new ListView<>();
		ListView<String> resultPListView = new ListView<>();
		ListView<String> resultSListView = new ListView<>();
		
		// Set the preferred width and height of all ListView (width: 400px, height(200px))
		matrix1ListView.setPrefWidth(400);  
		matrix2ListView.setPrefWidth(400); 
		resultPListView.setPrefWidth(400);  
		resultSListView.setPrefWidth(400);
		matrix1ListView.setPrefHeight(200);  
		matrix2ListView.setPrefHeight(200); 
		resultPListView.setPrefHeight(200);  
		resultSListView.setPrefHeight(200);
		
		// Add the title Matrix 1 into ListView
		matrix1ListView.getItems().add("Matrix 1");
		for(int i = 0; i < matrix1.length; i++)
		{
			// Create a StringBuilder to store contents of matrix1
			StringBuilder row = new StringBuilder();
			for(int j = 0; j < matrix2[0].length; j++)
			{
				row.append(String.format("%.2f", matrix1[i][j])).append(" ");
			}
			// Add the content in StringBuilder into ListView
			matrix1ListView.getItems().add(row.toString());
		}
		
		// Add the title Matrix 2 into ListView
		matrix2ListView.getItems().add("Matrix 2");
		for(int i = 0; i < matrix1.length; i++)
		{
			// Create a StringBuilder to store contents of matrix2
			StringBuilder row = new StringBuilder();
			for(int j = 0; j < matrix2[0].length; j++)
			{
				row.append(String.format("%.2f", matrix2[i][j])).append(" ");
			}
			// Add the content in StringBuilder into ListView
			matrix2ListView.getItems().add(row.toString());
		}
		
        // Parallel method
		long startTime1 = System.currentTimeMillis(); // Set the start time before running the multiplication of matrix1 and matrix2 
		resultMatrixP = parallelMultiplyMatrix(matrix1, matrix2); // Multiply matrix in parallel method
		long endTime1 = System.currentTimeMillis(); // Set the end time after running the multiplication of matrix1 and matrix2
		long timeUsed1 = endTime1 - startTime1; // Calculate the time used to complete the multiplication of matrix1 and matrix2
		
		// Sequential method
		long startTime2 = System.currentTimeMillis(); // Set the start time before running the multiplication of matrix1 and matrix2 
		resultMatrixS = sequenceMultiplyMatrix(matrix1, matrix2); // Multiply the matrix in sequence method
		long endTime2 = System.currentTimeMillis(); // Set the end time after running the multiplication of matrix1 and matrix2
		long timeUsed2 = endTime2 - startTime2; // Calculate the time used to complete the multiplication of matrix1 and matrix2
		
		// Add the title "Parallel Multiplication Result" in ListView
		resultPListView.getItems().add("Parallel Multiplication Result");
		for(int i = 0; i < resultMatrixP.length; i++)
		{
			// Create a StringBuilder to store multiplication matrix result that run in parallel
			StringBuilder row = new StringBuilder();
			for(int j = 0; j < resultMatrixP[i].length; j++)
			{
				row.append(String.format("%.2f", resultMatrixP[i][j])).append(" ");
			}
			// Add the content in StringBuilder into ListView
			resultPListView.getItems().add(row.toString());
		}
		
		// Add the title "Sequential Multiplication Result" in ListView
		resultSListView.getItems().add("Sequential Multiplication Result");
		for(int i = 0; i < resultMatrixS.length; i++)
		{
			// Create a StringBuilder to store multiplication matrix result that run in sequence
			StringBuilder row = new StringBuilder();
			for(int j = 0; j < resultMatrixS[i].length; j++)
			{
				row.append(String.format("%.2f", resultMatrixS[i][j])).append(" ");
			}
			// Add the content in StringBuilder into ListView
			resultSListView.getItems().add(row.toString());
		}
		
		// Create a button "Result"
		Button btnResult = new Button("Result");
		
		// Create HBox to hold matrix1 and matrix2 ListView
		HBox hbox1 = new HBox(10, matrix1ListView, matrix2ListView);
		// Create HBox to hold matrix multiplication result that run in parallel and in sequence 
		HBox hbox2 = new HBox(10, resultPListView, resultSListView);
		// Create VBox to hold both HBox for purpose centralization in the stage
		VBox vboxCenter = new VBox(20, hbox1, hbox2);
		
		// Create a BorderPane
		BorderPane bp = new BorderPane();
		bp.setCenter(vboxCenter); // Center the VBox at the stage
		bp.setBottom(btnResult); // Set the button "Result" at the bottom of the stage
		BorderPane.setAlignment(btnResult, Pos.CENTER); // Set the button "Result" at the center (bottom center) 
		BorderPane.setMargin(btnResult, new Insets(20, 0, 0, 0)); // Set the margin of button
		bp.setPadding(new Insets(20)); // Set the padding of BorderPane
		
		// Create and set the scene
		Scene scene = new Scene(bp);
		primaryStage.setTitle("Matrix and Multiplication Result");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Event handler of button "Result"
		btnResult.setOnAction(e -> {
			// Create the second stage                                         
			Stage secondStage = new Stage();
			
			// Create Label to hold number of processors, time used to multiply matrix in parallel and in sequence
			Label labelProcessor = new Label("The number of processors is " + String.valueOf(Runtime.getRuntime().availableProcessors()));
			Label labelTimeParallel = new Label("Time is " + String.valueOf(timeUsed1) + " milliseconds");
			Label labelTimeSequence = new Label("Sequential time is " + String.valueOf(timeUsed2) + " milliseconds");
			
			// Create VBox to hold all the Label
			VBox vbox2 = new VBox(5, labelProcessor, labelTimeParallel, labelTimeSequence);
			vbox2.setPadding(new Insets(20)); // Set the padding of VBox
			
			// Create and set the scene
			Scene scene2 = new Scene(vbox2, 300, 100);
			secondStage.setTitle("Show Result");
			secondStage.setScene(scene2);
			secondStage.show();
		});
		
		
		
		
	}
	// Parallel matrix multiplication
	public static double [][] parallelMultiplyMatrix(double[][]matrix1, double [][]matrix2)
	{
		// Create 2D array of result
		double [][] result = new double[matrix1.length][matrix2[0].length];
		
		// Create a RecursiveTask to handle matrix multiplication
		RecursiveAction mainTask = new MultiplyMatrix(matrix1, matrix2, result, 0, matrix1.length, 0, matrix2[0].length);
		
		// Create ForkJoinPool to execute task in parallel
		ForkJoinPool pool = new ForkJoinPool(); 
		pool.invoke(mainTask); // Start the task execution and wait for completion
		
		return result; // Return the result matrix after multiplication
	}
	
	public static class MultiplyMatrix extends RecursiveAction
	{
		// Data fields
		// Set the threshold (minimum row and column) to 100
		private final int THRESHOLD = 100;
		private double [][] matrix1, matrix2, result; 
		private int startRow, endRow, startColumn, endColumn;
		
		// Constructor with parameters
		MultiplyMatrix(double [][] matrix1, double [][] matrix2, double [][] result, int startRow, int endRow, int startColumn, int endColumn)
		{
			this.matrix1 = matrix1;
			this.matrix2 = matrix2;
			this.result = result;
			this.startRow = startRow;
			this.endRow = endRow;
			this.startColumn = startColumn;
			this.endColumn = endColumn;
		}
		
		// Defines the logic of task execution for RecursiveTask
		@Override
		protected void compute()
		{
			// Calculate total row and column in matrix
			int rowCount = endRow - startRow;
			int columnCount = endColumn - startColumn;
			
			// If the row and column lesser than threshold, then multiply the matrix in sequence
			if(rowCount < THRESHOLD && columnCount < THRESHOLD)
			{
				for(int row = startRow; row < endRow; row++)
				{
					for(int column = startColumn; column < endColumn; column++)
					{
						for(int i = 0; i < matrix2.length; i++)
						{
							result[row][column] += matrix1[row][i] * matrix2[i][column];
						}
					}
				}
			}
			
			// If the row and column greater or equal to threshold, split the task into smaller task, execute subtask and combine the results
			else
			{
				// Calculate middle row and column
				int middleRow = (startRow + endRow) / 2;
				int middleColumn = (startColumn + endColumn) / 2;
				
				// Create object of MultiplyMatrix
				MultiplyMatrix upperLeft = new MultiplyMatrix(matrix1, matrix2, result, startRow, middleRow, startColumn, middleColumn);
				MultiplyMatrix upperRight = new MultiplyMatrix(matrix1, matrix2, result, startRow, middleRow, middleColumn, endColumn);
				MultiplyMatrix lowerLeft = new MultiplyMatrix(matrix1, matrix2, result, middleRow, endRow, startColumn, middleColumn);
				MultiplyMatrix lowerRight = new MultiplyMatrix(matrix1, matrix2, result, middleRow, endRow, middleColumn, endColumn);
				
				// Divide into 4 subtasks for parallel computation (run asynchronously)
				upperLeft.fork();
				upperRight.fork();
				lowerLeft.fork();
				lowerRight.fork();
				
				// Wait for all subtasks to finish
				upperLeft.join();
				upperRight.join();
				lowerLeft.join();
				lowerRight.join();
			}
			
		}
	}
	
	// Sequential matrix multiplication
	public static double [][] sequenceMultiplyMatrix(double [][] matrix1, double [][] matrix2)
	{
		// Create 2D array to hold multiplication matrix result
		double [][] result = new double[matrix1.length][matrix2[0].length];
		
		for(int row = 0; row < matrix1.length; row++)
		{
			for(int column = 0; column < matrix2[0].length; column++)
			{
				for(int i = 0; i < matrix2.length; i++)
				{
					result[row][column] += matrix1[row][i] * matrix2[i][column];
				}
			}
		}
		return result;
	}
}
