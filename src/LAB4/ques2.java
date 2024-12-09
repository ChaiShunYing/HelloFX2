package LAB4;
//Name: Chai Shun Ying
//Matric: 224414
//Lab information: Lab 4 Ques 2
//Date: 6/12/2024
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class ques2 extends Application{
	public static void main(String[]args)
	{
		launch(args); // launch JavaFX application
	}
	
	private static Integer total = 0; // Wrapper object for total
	public void start(Stage primaryStage)
	{
		// Create a label to hold instruction for user understanding
		Label instructionLabel = new Label("Instruction: \n1. Press the buttons to run 400 threads. \n2. Each thread adds 20 to the total");
		instructionLabel.setFont(Font.font("Times New Roman",16));
		instructionLabel.setPadding(new Insets(0, 0, 20, 0));
		
		// Create label to hold total
		Label labelResult = new Label("Total: 0");
		labelResult.setFont(Font.font("Times New Roman", 14));
		labelResult.setTextFill(Color.BLUE);
		
		// Create button to start with or without synchronization
		Button btnWithoutSync = new Button("Run without Synchronization");
		Button btnWithSync = new Button("Run with Synchronization");
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(btnWithoutSync, btnWithSync);
		
		// Event handler for running without synchronization
		btnWithoutSync.setOnAction(e ->{
			total = 0;
			labelResult.setText("Running without synchronization");
			runThreads(false, labelResult);
		});
		
		// Event handler for running with synchronization
		btnWithSync.setOnAction(e ->{
			total = 0;
			labelResult.setText("Run with synchronization");
			runThreads(true, labelResult);
		});
		
		// Create VBox to hold all elements
		VBox mainLayout = new VBox(10, instructionLabel, vbox, labelResult);
		mainLayout.setPadding(new Insets(20));
		
		// Create and set the scene
		Scene scene = new Scene(mainLayout);
		primaryStage.setTitle("Synchronization Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Method to run the threads with 2 condition, which are with and without synchronization
	public static void runThreads(boolean isUseSync, Label labelResult)
	{
		Thread [] thread = new Thread[400]; // Store 400 threads
		
//		for(int i = 0; i < 400; i++)
//		{
//			thread[i] = new Thread(() ->{
//				if(useSync)
//					synchronizedAdd();
//				else
//					unsynchronizeAdd();
//			});
//		} or
		
		for(int i = 0; i < 400; i++)
		{
			// synchronization
			if(isUseSync)
			{
				// or thread[i] = new Thread(() -> addWithSynchronization()): public Thread(Runnable target)
				// () -> addWithSynchronization = public void run() {addWithSynchronization();};
				Runnable task1 = new Runnable() {
					public void run()
					{
						addWithSynchronization();
					}
				};
					
				thread[i] = new Thread(task1);
				
			}
			// without synchronization
			else
			{
				Runnable task2 = new Runnable() {
					public void run()
					{
						addWithoutSynchronization();
					}
				};
				thread[i] = new Thread(task2);
			}
			
			Platform.runLater(() -> labelResult.setText("Total: " + total)); // Safely update UI on JavaFX Application Thread
			
			thread[i].start(); // start the thread
		}
		
		// Wait for all threads to finish before proceeding
		for(Thread t: thread)
		{
			try
			{
				t.join(); // Wait for the thread to finish
			}
			// Handle InterruptedException
			catch(InterruptedException ie)
			{
				ie.getMessage();
			}
		}
		
	}
	// Method without synchronization
	private static void addWithoutSynchronization()
	{
		total += 20;
	}
	
	// Method with synchronization
	private static synchronized void addWithSynchronization()
	{
		total += 20;
	}
}
