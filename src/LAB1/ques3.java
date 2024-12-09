package LAB1;
//Name: Chai Shun Ying
//Matric Num: 224414
//purpose: calculate total sale with input unit price and quantity
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ques3 extends Application{
	public void start(Stage stage)
	{
		// Create a gp (grid pane) and set its properties
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(20, 10, 10, 20)); // set padding for the GridPane (top, right, bottom, left)
		gp.setHgap(10); // Set horizontal gap between columns
		gp.setVgap(10); // Set vertical gap between rows
		
		// PLace nodes (Label, TextField, HBox) in the gp 
		TextField unitPrice = new TextField();
		gp.add(new Label("Unit price of a product (RM): "), 0, 0);
		gp.add(unitPrice, 1, 0);
		
		TextField quantity = new TextField();
		gp.add(new Label("Quantity of the product sold: "), 0, 1);
		gp.add(quantity, 1, 1);
		
		Text totalSale = new Text();
		//Label totalSale = new Label();
		totalSale.setFont(new Font("Times New Roman", 16));
		totalSale.setStyle("-fx-fill: blue;");
		gp.add(totalSale, 0, 3);
		
		// Create HBox for the buttons with a spacing of 5 pixels between them
		HBox buttonPane = new HBox(5);
		
		// Create button
		Button calcButton = new Button("Calculate"); // "Calculate" button
		Button clearButton = new Button("Clear"); // "Clear" button
	
		// Add both Calculate and Clear buttons to the Hbox 
		buttonPane.getChildren().addAll(calcButton, clearButton); 
		gp.add(buttonPane, 0, 2); // Add the Hbox containing the buttons to the gp at row 2, column 0
		
		// Set action for the Calculate button to calculate the totalSale
		calcButton.setOnAction
		(e -> {
			try
			{
				{
					// convert unitPrice and quantity from String to double to calculate totalSale
					double unitPriceDouble  = Double.parseDouble(unitPrice.getText());
					int quantityDouble = Integer.parseInt(quantity.getText());
					double totalSaleDouble = unitPriceDouble * quantityDouble;

					// Place totalSale to totalSaleLabel
					totalSale.setText("Total Sale: RM " + String.format("%.2f", totalSaleDouble));
				}
			}
			// handle NumberFormatException when the unitPrice and quantity are not insert with double data type
			catch(NumberFormatException nfe)
			{
				System.out.println("Please enter valid number of unit price and quantity instead of letters.");
			}
		});
		
		// Set action of Clear button to reset all the text fields and label
		clearButton.setOnAction
		(e ->{
			unitPrice.clear(); // Clear the text field of unitPrice
			quantity.clear(); // Clear the text field of quantity
			totalSale.setText(""); // Clear the text inside the totalSaleLabel label
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(gp); 
		stage.setTitle("Total Sale"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
		
	}
	public static void main(String[]args)
	{
		launch(args);
	}
}

