package LAB4;
//Name: Chai Shun Ying
//Matric: 224414
//Lab information: Lab 4 Ques 1a
//Date: 6/12/2024
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ques1_1 extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		// Create title for primary stage
		Label titleInStage = new Label("Data Collection");
		titleInStage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		
		// Create hbox to hold and center the title of primary stage
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(titleInStage);
		
		// Create TextField for user input: loop counts and content for character, number and string
		TextField textTimeChar = new TextField(); // input loop count for characters
		TextField textTimeNum = new TextField(); // input loop count for numbers
		TextField textTimeStr = new TextField(); // input loop count for strings
		
		TextField textChar = new TextField(); // input character to be printed
		TextField textNum = new TextField("Random number (1-50)"); // display information of random number range
		textNum.setEditable(false); // set the TextField of textNum to non-editable 
		TextField textString = new TextField(); // input string to be printed 
		
		// Create GridPane to hold input labels and text fields
		GridPane gp1 = new GridPane();
		// set horizontal and vertical gap between cells
		gp1.setHgap(10); 
		gp1.setVgap(10);
		gp1.add(new Label("Loop count (e.g. 10)"), 1, 0);
		gp1.add(new Label("Content"), 2, 0);
		gp1.add(new Label("Character"), 0, 1);
		gp1.add(new Label("Number"), 0, 2);
		gp1.add(new Label("String"), 0, 3);
		gp1.add(textTimeChar, 1, 1);
		gp1.add(textTimeNum, 1, 2);
		gp1.add(textTimeStr, 1, 3);
		gp1.add(textChar, 2, 1);
		gp1.add(textNum, 2, 2);
		gp1.add(textString, 2, 3);
		
		// Create VBox to hold GridPane and title in HBox
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(hbox, gp1);
		
		// Create buttons for output and cleaing inputs
		Button btnOutput = new Button("Output");
		Button btnClear = new Button("Clear");
		
		// Create Hbox to hold buttons
		HBox bottomVbox = new HBox(10, btnOutput, btnClear);
		bottomVbox.setAlignment(Pos.BOTTOM_CENTER);
		
		// Combine all components into a VBox
		VBox mainLayout1 = new VBox(20, vbox, bottomVbox);
		mainLayout1.setPadding(new Insets(20));
		
		// Create and set the scene for the primary stage
		Scene scene = new Scene(mainLayout1);
		primaryStage.setTitle("User Input");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Event handler for "Output" button
		btnOutput.setOnAction(e ->{
			// Create a new stage to display output
			Stage secondStage = new Stage();
			try
			{
				// Convert the TextField input from users for loops and content
				int timeChar = Integer.parseInt(textTimeChar.getText());
				int timeNum = Integer.parseInt(textTimeNum.getText());
				int timeString = Integer.parseInt(textTimeStr.getText());
				
				// Make sure character is a letter and the length is only 1
				String charInput = textChar.getText();
				if(charInput.length() != 1)
					throw new IllegalArgumentException("Error. Please enter exactly one character.");
				char contentChar = textChar.getText().charAt(0);
				if(!Character.isLetter(contentChar))
					throw new IllegalArgumentException("Error. Please enter a valid letter.");
				
				String contentString = textString.getText();
			
				// Create a FlowPane for output display
				FlowPane outputPane = new FlowPane();
				outputPane.setPadding(new Insets(10)); // Padding around the pane
				outputPane.setPrefWrapLength(350); // Set wrap width for automatic line wrapping

				// Create tasks for printing character, number and string
				Runnable printChar = new PrintChar(contentChar, timeChar, outputPane);
				Runnable printInt = new PrintNum(timeNum, outputPane);
				Runnable printStr = new PrintString(contentString, timeString, outputPane);
	        
				// Create threads to hold each task
				Thread thread1 = new Thread(printChar);
				Thread thread2 = new Thread(printInt);
				Thread thread3 = new Thread(printStr);
			
				// Start the threads for each task based on run() method
				thread1.start();
				thread2.start();
				thread3.start();
			
				// Display the output stage
				VBox pane2 = new VBox();
				pane2.getChildren().add(outputPane);
				pane2.setPadding(new Insets(10));

	        	// Create and set the scene for the secondStage
	        	Scene secondScene = new Scene(pane2, 400, 300);
	        	secondStage.setTitle("Concurrent Output");
	        	secondStage.setScene(secondScene);
	        	secondStage.show();
			}
			// Handle NumberFormatException
	        catch(NumberFormatException nfe)
			{
				showErrorMessage("Error. Please enter valid numbers for loop counts.", secondStage);
			}
			// Handle IllegalArgumentException
			catch(IllegalArgumentException iae)
			{
				showErrorMessage(iae.getMessage(), secondStage);
			}
		});
		
		// Event handler for "Clear" button
		btnClear.setOnAction(e ->{
			textTimeChar.clear();
			textTimeNum.clear();
			textTimeStr.clear();
			textChar.clear();
			textString.clear();
		});
	}
	
	// Method to show error messages
	private void showErrorMessage(String message, Stage parentStage)
	{
		// Create label and set style of the text to hold error message
		Label labelError = new Label(message);
		labelError.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
		labelError.setTextFill(Color.RED);
			
		// Create VBox to handle and center error message
		VBox errorPane = new VBox();
		errorPane.setAlignment(Pos.CENTER);
		errorPane.getChildren().add(labelError);
			
		// Place the error message on the second stage
		Scene errorScene = new Scene(errorPane, 400, 300);
		parentStage.setTitle("Error");
		parentStage.setScene(errorScene);
		parentStage.show();
	}
}

// Class PrintChar
class PrintChar implements Runnable
{
	// Data fields
	private char charToPrint;
	private int times;
	private FlowPane outputPane;
	
	// Constructor with parameters
	public PrintChar(char c, int t, FlowPane outputPane)
	{
		charToPrint = c;
		times = t;
		this.outputPane = outputPane;
	}
	
	@ Override
	public void run()
	{
		for(int i = 0; i < times; i++)
		{
			Label label = new Label(charToPrint + ", ");
			Platform.runLater(() -> outputPane.getChildren().add(label)); // Safely update UI on JavaFX Application Thread
		}
	}
}

// Class PrintNum
class PrintNum implements Runnable
{
	// Data fields
	private int times;
	private FlowPane outputPane;
	
	// Constructor with parameters
	public PrintNum(int t, FlowPane outputPane)
	{
		times = t;
		this.outputPane = outputPane;
	}
	
	@ Override
	public void run()
	{
		for(int i = 0; i < times; i++)
		{
			int randomInt = (int)(Math.random() * (50 - 1)) + 1; // Create randon number for each time loop (1-50)
			Label label = new Label("<" + randomInt + "> ");
			Platform.runLater(() -> outputPane.getChildren().add(label)); // Safely update UI on JavaFX Application Thread
		}
	}
}

// Class PrintString
class PrintString implements Runnable
{
	// Data fields
	private String strToPrint;
	private int times;
	private FlowPane outputPane;
	
	// Constructor with parameters
	PrintString(String s, int t, FlowPane outputPane)
	{
		strToPrint = s;
		times = t;
		this.outputPane = outputPane;
	}
	
	@ Override
	public void run()
	{
		for(int i = 0; i < times; i++)
		{
			Label label = new Label(strToPrint + ", ");
			Platform.runLater(() -> outputPane.getChildren().add(label)); // Safely update UI on JavaFX Application Thread
		}
	}
}
