package LAB2;
//Name: Chai Shun Ying
//Matric Num: 224414
//purpose: calculate total sale with input unit price and quantity
//Submission date: 27/11/2024 (change discount range)
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ques2 extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage stage)
	{
		Label productCodeLabel = new Label("Product code: ");
		TextField productCodeText = new TextField();
		Label productNameLabel = new Label("Product name: ");
		TextField productNameText = new TextField();
		Label unitPriceLabel = new Label("Price per unit(RM): ");
		TextField unitPriceText = new TextField();
		Label quantityLabel = new Label("Quantity: ");
		TextField quantityText = new TextField();
		
		GridPane gp = new GridPane();
		gp.setVgap(10);
		gp.setHgap(10);
		
		gp.add(productCodeLabel, 0, 0);
		gp.add(productNameLabel, 0, 1);
		gp.add(unitPriceLabel, 0, 2);
		gp.add(quantityLabel, 0, 3);
		
		gp.add(productCodeText, 1, 0);
		gp.add(productNameText, 1, 1);
		gp.add(unitPriceText, 1, 2);
		gp.add(quantityText, 1, 3);
		
		Button calcButton = new Button("Calculate");
		Button clearButton = new Button("Clear");
		HBox buttonRow = new HBox(10);
		buttonRow.setAlignment(Pos.CENTER);
		buttonRow.getChildren().addAll(calcButton, clearButton);
		
		Label outputLabel = new Label();
		outputLabel.setStyle("-fx-text-fill:purple;");
		
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(30));
		vbox.getChildren().addAll(gp, buttonRow, outputLabel);
	
		
		// Set action for the Calculate button to calculate the totalSale
		calcButton.setOnAction
		(e -> {
			try
			{
				{
					// convert unitPrice and quantity from String to double to calculate totalSale
					double unitPrice  = Double.parseDouble(unitPriceText.getText());
					int quantity = Integer.parseInt(quantityText.getText());
					double totalSale = unitPrice * quantity;

					if(unitPrice < 0 || quantity < 0)
					{
						outputLabel.setText("Please enter positive values for unit price and quantity");
						outputLabel.setStyle("-fx-text-fill:red;");
						return;
					}
					
					double discount;
					if (totalSale >= 0 && totalSale <= 500) discount = 0.03;
					else if(totalSale > 500 && totalSale <= 5000) discount = 0.075;
					else if(totalSale > 5000 && totalSale <= 10000) discount = 0.1;
					else if(totalSale > 10000 && totalSale <= 15000) discount = 0.15;
					else if(totalSale > 15000) discount = 0.2;
					else discount = 0;
					
					double discountPrice = totalSale - (totalSale * discount);
					outputLabel.setText("Product code: " + productCodeText.getText() + "\nProduct name: " + productNameText.getText() + "\nPrice per unit: RM " + unitPriceText.getText() + "\nQuantity: " + quantityText.getText() +  "\nTotal Sale: RM " + String.format("%.2f", totalSale) + "\nDiscount: " + (discount * 100) + "%\nTotal price after discount: RM " + String.format("%.2f", discountPrice));
				}
			}
			// handle NumberFormatException when the unitPrice and quantity are not insert with double data type
			catch(NumberFormatException nfe)
			{
				outputLabel.setText("Please enter valid number of unit price and quantity.");
				outputLabel.setStyle("-fx-text-fill:red;");
			}
		});
		
		// Set action of Clear button to reset all the text fields and label
		clearButton.setOnAction
		(e ->{
			productCodeText.clear();
			productNameText.clear(); // clear the textField of name
			unitPriceText.clear(); // Clear the text field of unitPrice
			quantityText.clear(); // Clear the text field of quantity
			outputLabel.setText(""); // Clear the text inside the totalSaleLabel label
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(vbox, 400, 400); // scene width: 400, height: 400
		stage.setTitle("Product Price"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
		
	}
}
