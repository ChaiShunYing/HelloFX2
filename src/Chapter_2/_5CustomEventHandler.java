package Chapter_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
public class _5CustomEventHandler extends Application{

	public static void main(String[]args)
	{
		launch(args);
	}
	
	@ Override
	public void start(Stage primaryStage)
	{
		Button button = new Button("Click me");
		Label result = new Label();
		button.setOnAction(new ButtonClickHandler(result));
		
		// if want to use the method of HBox, must use the HBox instead of Pane
		// Pane mainLayout = new HBox(10); 
		HBox mainLayout = new HBox(); // HBox(spacing: double)
		mainLayout.setPadding(new Insets(20)); // import javafx.geometry.*;
		mainLayout.setAlignment(Pos.CENTER); 
		HBox.setMargin(button, new Insets(10));
		mainLayout.getChildren().addAll(button, result, new Label("OK bro"));
		
		Scene scene = new Scene(mainLayout, 400, 400);
		primaryStage.setTitle("Custom Event Handler");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		int num = (int) ((100 - 10) * (Math.random())) + 10;
		System.out.println(num);
		
	}
	
}

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
class ButtonClickHandler implements EventHandler<ActionEvent>
{
	private Label result;
	ButtonClickHandler()
	{}
	
	ButtonClickHandler(Label result)
	{
		this.result = result;
	}
	
	@ Override
	public void handle(ActionEvent event)
	{
		result.setText("Button clicked using Custom Event Handler!");
	}
}