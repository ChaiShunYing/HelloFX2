package LAB1;
// Name: Chai Shun Ying
// Matric Num: 224414
// purpose: display a 10-by-8 matrix with integer element between 2-10
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;

public class ques1 extends Application{
	@Override
	// Override the start method in Application class
	public void start(Stage stage)
	{
		// Create a gridPane and sets its properties 
		GridPane gridPane = new GridPane();
		
		// for loop of 10-by-8 matrix
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				// Create text fields with integer number 2-10
				TextField tf = new TextField();
				int randomNum = (int)(2 + ((11 - 2) * Math.random()));
				
				// Set the properties of tf 
				tf.setText(Integer.toString(randomNum));
				tf.setPrefWidth(40);
				tf.setPrefHeight(40);
				tf.setAlignment(Pos.CENTER); // or tf.setStyle("-fx-alignment: center;");
				tf.setEditable(false); // user cannot edit the text
				
				// Place nodes in the gridPane
				gridPane.add(tf, i, j);
			}
		}
		
		// Create a scene and place it in stage
		// Scene(Parent root(root nodes of Scene), double width, double height)
		Scene scene = new Scene(gridPane); 
		stage.setTitle("Lab 1 Assignment"); // Set the stage title
		stage.setScene(scene); // Place the scene in stage
		stage.show(); // Display the stage
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
}
