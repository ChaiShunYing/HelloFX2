package Chapter_1;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Application;
public class _5SetPrefWidth extends Application{

	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage stage)
	{
		VBox hbox = new VBox();
		hbox.setPrefWidth(1000);
		hbox.setPrefHeight(100);
		Button button = new Button("Submit");
		TextField textField = new TextField();
		Label label = new Label("Enter name:");

		button.setPrefWidth(100);    // Set preferred width for button
		textField.setPrefWidth(200); // Set preferred width for text field
		label.setPrefWidth(100);      // Set preferred width for label

		hbox.getChildren().addAll(label, textField, button);
		
		Scene scene = new Scene(hbox);
		stage.setScene(scene);
		stage.setTitle("Pref Width");
		stage.show();
	}
}
