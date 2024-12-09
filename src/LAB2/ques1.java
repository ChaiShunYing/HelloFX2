package LAB2;
// Name: Chai Shun Ying
// Matric: 224414
// purpose: Use BMI to identify BMI category, classification and risk
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

// outer class
public class ques1 extends Application{

	// main method to launch JavaFX application
	public static void main(String[] args) 
	{
		launch(args); // launch the JavaFX application by calling start()
	}
	
	// Declare instance at outer class
	TextField nameText, weightText, heightText;
	Label resultLabel;
	
	@ Override
	public void start(Stage primaryStage)
	{
		// VBox for Label("Name", "Weight", "Height") with 20 pixels spacing
		VBox vbox1 = new VBox(20);
		vbox1.getChildren().addAll(new Label("Name: "), new Label("Weight(kg): "), new Label("Height(m): "));
		
		// VBox for TextField to enter name, weight and height with 10 pixels spacing
		VBox vbox2 = new VBox(10);
		nameText = new TextField();
		weightText = new TextField();
		heightText = new TextField();
		vbox2.getChildren().addAll(nameText, weightText, heightText);
		
		// HBox for Button calculate and clear with 10 pixels spacing
		HBox buttonRow = new HBox(10);
		buttonRow.setPadding(new Insets(0, 0, 0, 20)); // Add left padding for 20 pixels (indent buttons)
		buttonRow.setAlignment(Pos.CENTER);
		Button calcButton = new Button("Calculate BMI"); 
		Button clearButton = new Button("Clear");
		buttonRow.getChildren().addAll(calcButton, clearButton);
		
		// Set action on buttons click
		calcButton.setOnAction(new CalculateHandler());
		clearButton.setOnAction(new ClearHandler());		
		
		// Label to display BMI information with 10 pixels top padding and 20 pixels left padding
		resultLabel = new Label(); 
		resultLabel.setPadding(new Insets(10, 0, 0, 20));
		resultLabel.setStyle("-fx-text-fill:blue;");
		
		// HBox to organize the labels and text fields horizontally 
		HBox hbox1 = new HBox(10);
		hbox1.setPadding(new Insets(20, 0, 0, 20));
		hbox1.getChildren().addAll(vbox1, vbox2);
		
		// VBox as main layout to organize all elements vertically
		VBox mainLayout = new VBox(15);
		mainLayout.getChildren().addAll(hbox1, buttonRow, resultLabel);
		
		// Set up the scene and stage
		Scene scene = new Scene(mainLayout, 400, 400); // Set scene size
		primaryStage.setTitle("BMI Calculator"); // Set title for window
		primaryStage.setScene(scene); // Set scene on primaryStage
		primaryStage.show(); // Display the stage
	}                                                                        
	
	// class CalculateHandler (inner class) to handle the event of calculate button click 
	class CalculateHandler implements EventHandler<ActionEvent>
	{	
		// Handles calculate button click event
		// Calculate BMI and display the result
		@ Override
		public void handle(ActionEvent e)
		{
			// try-catch block
			try
			{
				// change the text of weight and height to double data type to calculate BMI
				double weight = Double.parseDouble(weightText.getText());
				double height = Double.parseDouble(heightText.getText());
				double BMI = weight / (height * height);
				
				if(weight < 0 || height < 0)
				{
					resultLabel.setText("Please enter positive value for weight and height");
					resultLabel.setStyle("-fx-text-fill:red;");
					return;
				}
				
				// use if-else statement to identify category, classification and risk based on BMI
				String category, classification, risk;
				if(BMI < 18.5)
				{
					category = "< 18.5";
					classification = "Underweight";
					risk = "Increased";
				}
			
				else if(BMI >= 18.5 && BMI <= 24.9)
				{
					category = "18.5 - 24.9";
					classification = "Normal weight";
					risk = "Least";
				}
			
				else if(BMI >= 25.0 && BMI <= 29.9)
				{
					category = "25.0 - 29.9";
					classification = "Overweight";
					risk = "Increased";
				}
			
				else if(BMI >= 30.0 && BMI <= 34.9)
				{
					category = "30.0 - 34.9";
					classification = "Obese class I";
					risk = "High";
				}
			
				else if(BMI >= 35 && BMI <= 39.9)
				{
					category = "35.0 - 39.9";
					classification = "Obese class II";
					risk = "Very High";
				}
			
				else
				{
					category = ">= 40.0";
					classification = "Obese class III";
					risk = "Extremely high";
				}
				
				// display the result of name, BMI, category, classification and risk
				resultLabel.setText("Name: " + nameText.getText() + "\nBMI: " + String.format("%.1f", BMI) + "\nBMI category: " + category + "\nClassification: " + classification + "\nRisk: " + risk);
			}
			
			// Handle NumberFormatException
			catch(NumberFormatException nfe)
			{
				resultLabel.setText("Please enter valid values for height and weight");
				resultLabel.setStyle("-fx-text-fill:red;");
			}
		}
		 
	}
	
	// class ClearHandler(inner class) to handle the event of clear button click 
	class ClearHandler implements EventHandler<ActionEvent>
	{
		// Handles the clear button click event to clear the user input and result
		@ Override
		public void handle(ActionEvent e)
		{
			nameText.clear(); // clear name input
			weightText.clear(); // clear weight input
			heightText.clear(); // clear height input 
			resultLabel.setText(""); // clear text of result
		}
	}
}