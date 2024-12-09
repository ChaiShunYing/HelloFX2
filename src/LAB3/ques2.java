package LAB3;
//Name: Chai Shun Ying
//Matric: 224414
//Lab information: Lab 3 Ques 2
//Date: 27/11/2024_modify discount
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;

public class ques2 extends Application{
	// main method to launch JavaFX application
	public static void main(String[] args) 
	{
		launch(args); // launch the JavaFX application by calling start()
	}
	
	// Array of data: codes, names, unit prices
	private String [] productCode = {"T001", "L001", "S001", "B001"};
	private String [] productName = {"Television", "Laptop", "Smartphone", "Book"};
	private double [] unitPrice = {7000.0, 6000.0, 3000.0, 10.0};
	
	// Path to the product video 
	private String videoURL = new File("C:\\ProductVideo.mp4").toURI().toString();
	
	public void start(Stage stage)
	{
		// Create a ComboBox for product selection
		ComboBox <String> cbo = new ComboBox<>();
		ObservableList <String> items = FXCollections.observableArrayList(productName); // Create an ObservableList using the product name as the initial reference data
		cbo.setItems(items); // Set the ComboBox options to display the list of product names
		cbo.setValue("Television"); // Set default selection
		
		// HBox to hold selection of product
		HBox productSelection = new HBox(10);
		productSelection.getChildren().addAll(new Label("Select Product: "), cbo);
				
		// VBox that hold Label
		VBox vbox1 = new VBox(20);
		vbox1.getChildren().addAll(
			new Label("Product Code: "),
			new Label("Product Name: "),
			new Label("Unit Price: "),
			new Label("Quantity: ") 
		);
		
		// VBox that hold the TextField for displaying product details
		// The default details are "Television"
		VBox vbox2 = new VBox(10); // 10 px spacing
		TextField productCodeTf = new TextField(productCode[0]);
		productCodeTf.setEditable(false); // Make non-editable
		TextField productNameTf = new TextField(productName[0]);
		productNameTf.setEditable(false); // Make non-editable
		TextField unitPriceTf = new TextField(String.valueOf(unitPrice[0]));
		unitPriceTf.setEditable(false);
		TextField quantityTf = new TextField(); // Editable field for quantity 
		vbox2.getChildren().addAll(productCodeTf, productNameTf, unitPriceTf, quantityTf);
		
		// HBox to hold Label and TextField
		HBox hbox = new HBox(10); // 10 px spacing
		hbox.getChildren().addAll(vbox1, vbox2);
		
		// HBox to hold Buttons for calculate and clear
		HBox hboxButton = new HBox(10); // 10 px spacing
		hboxButton.setAlignment(Pos.CENTER);
		Button calcButton = new Button("Calculate");
		Button clearButton = new Button("Clear");
		hboxButton.getChildren().addAll(calcButton, clearButton);
		
		// Label to display calculation results
		Label result = new Label();
		result.setStyle("-fx-text-fill:blue;");
		
		// Set action for the Calculate button 
		calcButton.setOnAction (e -> {
			try
			{
				// convert unitPrice and quantity from String to double to calculate totalSale
				double unitPrice = Double.parseDouble(unitPriceTf.getText());
				int quantity = Integer.parseInt(quantityTf.getText());
				double totalSale = unitPrice * quantity;
				
				// Validate inputs for positive values
                if (quantity < 0) 
                {
                    result.setText("Please enter positive quantity value.");
                    result.setStyle("-fx-text-fill:red;");
                    return;
                }

				// Determine discount based on total sale
				double discount;
				if (totalSale >= 0 && totalSale <= 500) discount = 0.03;
				else if(totalSale > 500 && totalSale <= 5000) discount = 0.075;
				else if(totalSale > 5000 && totalSale <= 10000) discount = 0.1;
				else if(totalSale > 10000 && totalSale <= 15000) discount = 0.15;
				else if(totalSale > 15000) discount = 0.2;
				else discount = 0;
				
				// Calculate final price after discount
				double discountPrice = totalSale - (totalSale * discount);
				
				// Display results
				result.setText("Product code: " + productCodeTf.getText() + "\nProduct name: " + productNameTf.getText() + "\nPrice per unit: RM " + unitPrice + "\nQuantity: " + quantity +  "\nTotal Sale: RM " + String.format("%.2f", totalSale) + "\nDiscount: " + (discount * 100) + "%\nTotal price after discount: RM " + String.format("%.2f", discountPrice));
				
			}
			// handle NumberFormatException when the unitPrice and quantity are not insert with double data type
			catch(NumberFormatException nfe)
			{
				result.setText("Make sure unit price and quantity are valid values.");
				result.setStyle("-fx-text-fill:red;");
			}
		});
		
		// Set action of clear button
		// Clear button resets all inputs and results
		clearButton.setOnAction
		(e ->{
			cbo.getSelectionModel().clearSelection(); // Clear the current selection in the ComboBox
			productCodeTf.clear();
			productNameTf.clear();
			unitPriceTf.clear();
			quantityTf.clear();
			result.setText(""); // Clear result label
		});
		
		// Update product details when a product is selected
		cbo.setOnAction(e ->{
		// Returns the currently selected value from the ComboBox
		String selectedProductName = cbo.getValue();
		int selectedIndex = -1;
		            
		// Find the index of the selected product
		for (int i = 0; i < productName.length; i++) 
		{
			if (productName[i].equals(selectedProductName)) 
			{
				selectedIndex = i;
				break;
			}
		}
		
		// Update TextFields based on selected product
		if (selectedIndex != -1) 
		{
			productCodeTf.setText(productCode[selectedIndex]);
			productNameTf.setText(productName[selectedIndex]);
			unitPriceTf.setText(String.valueOf(unitPrice[selectedIndex]));
		}
		});
		
		// VBox to hold all the components except MediaView
		VBox vbox3 = new VBox(10);
		vbox3.getChildren().addAll(productSelection, hbox, hboxButton, result);
		vbox3.setPadding(new Insets(20));
		
		// Media player to display video
		Media productMedia = new Media(videoURL); // Load the media
		MediaPlayer mediaPlayer = new MediaPlayer(productMedia); // Control the video
		MediaView mediaView = new MediaView(mediaPlayer); // Display the video
		mediaView.setFitHeight(250); // Set height of the video
		mediaView.setFitWidth(300); // Set width of the video
		mediaView.setPreserveRatio(true); // Preserve the aspect ratio of the video
		
		mediaPlayer.setAutoPlay(true); // Automatically play the video
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repeat the video indefinitely
		
		// Combine all components into a BorderPane layout
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(vbox3);
		mainLayout.setRight(mediaView);
		BorderPane.setMargin(mediaView, new Insets(50, 10, 0, 0));
		
		// Create a Timeline animation for a blinking effect using EventHandler
		String title = "Product Price";
		EventHandler<ActionEvent> eventHandler = e ->
		{
			// if title is empty, the title to "Product Sale"
			if(stage.getTitle().isEmpty())
				stage.setTitle(title);
			// if title is not empty, set title to blank
			else
				stage.setTitle("");	
		};
				
		// Create the animation
		// The duration between each keyframe is 0.5s
		// The eventHandler is used to toggle between empty title and string of title for blinking effect (trigger every 0.5s)
		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.5), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE); // The title will blink continuously 
		animation.play(); // Start animation		
		
		// Create and display the scene
		Scene scene = new Scene(mainLayout, 640, 400); // scene width: 600, height: 400
		stage.setTitle(title); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
		
	}
	
}