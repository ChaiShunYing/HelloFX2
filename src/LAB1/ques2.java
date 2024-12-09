package LAB1;
// Name: Chai Shun Ying
// Matric Num: 224414
// purpose: calculate BMI of user with input height and weight
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
import javafx.scene.text.Font;

public class ques2 extends Application{
	public void start(Stage stage)
	{
		// Create a gp (grid pane) and set its properties
		GridPane gp = new GridPane();
		gp.setHgap(10); // Set horizontal gap between columns
		gp.setVgap(10); // Set vertical gap between rows
		
		// PLace nodes in the gp
		gp.add(new Label("User ID: "), 0, 0);
		TextField userID = new TextField();
		gp.add(userID, 1, 0);
		
		gp.add(new Label("Name: "), 0, 1);
		TextField name = new TextField();
		gp.add(name, 1, 1);
		
		gp.add(new Label("Weight(kg): "), 0, 2);
		TextField weight = new TextField();
		gp.add(weight, 1, 2);
		
		gp.add(new Label("Height(m): "), 0, 3);
		TextField height = new TextField();
		gp.add(height, 1, 3);
		
		Button calcButton = new Button("Calculate");
		gp.add(calcButton, 0, 4);
		
		Label BMI = new Label();
		BMI.setFont(new Font("Times New Roman", 16));
		BMI.setStyle("-fx-text-fill: blue;");
		
		Button clearButton = new Button("Clear");
		gp.add(clearButton, 1, 4);
		
		VBox mainLayout = new VBox(10);
		mainLayout.setPadding(new Insets(20)); // Set padding of top, left, right, bottom with 20 pixels
		mainLayout.getChildren().addAll(gp, BMI);
		
		// Set action for the Calculate button
		calcButton.setOnAction
		(e ->{
			try
			{
				{
					// convert weight and height from String to double to calculate BMI
					double weightDouble = Double.parseDouble(weight.getText());
					double heightDouble = Double.parseDouble(height.getText());
					double BMIDouble = weightDouble / (heightDouble * heightDouble);
					String status;
					// if-else statement to determine status based on BMI
					if(BMIDouble < 18.5)
						status = "Underweight";
					else if(BMIDouble >= 18.5 && BMIDouble <= 24.9)
						status = "Normal";
					else if(BMIDouble > 24.9 && BMIDouble <= 29.9)
						status = "Over Weight";
					else if(BMIDouble > 29.9 && BMIDouble <= 34.9)
						status = "Obese";
					else
						status = "Severely Obese";

					// Place BMIDouble and status to BMIResultLabel
					BMI.setText("BMI: " + String.format("%.2f", BMIDouble) + "\nStatus: " + status);
				}
			}
			// handle NumberFormatException when the weight and height are not insert with double data type
			catch(NumberFormatException nfe)
			{
				System.out.println("Please enter valid number for weight and height instead of letters.");
			}
		});
		
		// Set action for Clear button to reset all text fields and label
		clearButton.setOnAction
		(e -> {
			userID.clear(); // clear text field of userID
			name.clear(); // clear text field of name
			weight.clear(); // clear text field of weight
			height.clear(); // clear text field of height
			BMI.setText(""); // clear the text inside BMIResultLabel label
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(mainLayout, 300, 300); 
		stage.setTitle("BMI Calculator"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage	
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
}
