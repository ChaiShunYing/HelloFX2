package LAB3;
import javafx.scene.layout.BorderPane;
// Name: Chai Shun Ying
// Matric: 224414
// Lab information: Lab 3 Ques 1
// Date: 24/11/2024

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Scene;
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

//outer class
public class ques1 extends Application{

	// main method to launch JavaFX application
	public static void main(String[] args) 
	{
		launch(args); // launch the JavaFX application by calling start()
	}
	
	// Declare instance at outer class
	TextField nameText, weightText, heightText; // TextField for user inputs
	Label resultLabel; // Label to display BMI result
	private String videoURL = new File("C:\\Exercise Video.mp4").toURI().toString(); // URL for exercise video
	
	@ Override
	public void start(Stage primaryStage)
	{
		// VBox to hold Label 
		VBox vbox1 = new VBox(20); // 20 px spacing
		vbox1.setPadding(new Insets(10, 0, 0, 10));
		vbox1.getChildren().addAll(new Label("Name: "), new Label("Weight(kg): "), new Label("Height(m): "));
		
		// VBox to hold TextField
		VBox vbox2 = new VBox(10); // 10 px spacing
		vbox2.setPadding(new Insets(10, 0, 0, 0));
		nameText = new TextField();
		weightText = new TextField();
		heightText = new TextField();
		vbox2.getChildren().addAll(nameText, weightText, heightText);
		
		// HBox to hold Button calculate and clear
		HBox buttonRow = new HBox(10); // 10 px spacing
		buttonRow.setAlignment(Pos.CENTER);
		Button calcButton = new Button("Calculate BMI"); 
		Button clearButton = new Button("Clear");
		buttonRow.getChildren().addAll(calcButton, clearButton);
		
		// Set action on buttons click
		calcButton.setOnAction(new CalculateHandler());
		clearButton.setOnAction(new ClearHandler());
		
		// Label to display BMI information
		resultLabel = new Label(); 
		resultLabel.setPadding(new Insets(0, 0, 0, 20));
		resultLabel.setStyle("-fx-text-fill:blue;");
		
		// HBox to organize the labels and text fields horizontally 
		HBox hbox1 = new HBox(10); // 10 px spacing
		hbox1.setPadding(new Insets(10, 0, 0, 10));
		hbox1.getChildren().addAll(vbox1, vbox2);
		
		// MediaPlayer to display exercise video
		Media exerciseMedia = new Media(videoURL); // Load the media
		MediaPlayer mediaPlayer = new MediaPlayer(exerciseMedia); // Control the video
		MediaView mediaView = new MediaView(mediaPlayer); // Display the video
		mediaView.setFitHeight(200); // Set height of the video
		mediaView.setFitWidth(250); // Set width of the video
		mediaView.setPreserveRatio(true); // Preserve the aspect ratio of the video
		
		mediaPlayer.setAutoPlay(true); // Automatically play the video
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repeat the video indefinitely
		
		VBox vbox3 = new VBox(10);
		vbox3.getChildren().addAll(hbox1, buttonRow, resultLabel);
		
		// Combine all components into a BorderPane layout
		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(vbox3);
		mainLayout.setBottom(mediaView);
		BorderPane.setAlignment(mediaView, Pos.CENTER);
		BorderPane.setMargin(mediaView, new Insets(0, 0, 10, 0)); // Top, Right, Bottom, Left
		
		// Create a Timeline animation for a blinking effect using EventHandler
		String title = "BMI Calculator";
		EventHandler<ActionEvent> eventHandler = e ->
		{
			// if title is not empty, set title to blank
			if(primaryStage.getTitle().length() != 0)
				primaryStage.setTitle("");
			// if title is empty, set title to "BMI Calculator"
			else
				primaryStage.setTitle(title);	
		};
		
		// Create the animation
		// The duration between each keyframe is 0.5s
		// The eventHandler is used to toggle between empty title and string of title for blinking effect (trigger every 0.5s)
		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.5), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE); // The title will blink continuously 
		animation.play(); // Start animation
		
		// Set up the scene and stage
		Scene scene = new Scene(mainLayout, 370, 410); // Set scene size
		primaryStage.setTitle(title); // Set title for window
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
	
	


