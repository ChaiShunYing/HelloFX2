package Chapter_2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

public class _6AnonymousInnerClass extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		Button button = new Button("Click me");
		Label result = new Label();
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event)
			{
				result.setText("Button clicked using Anonymous Inner Class!");
			}
		});
		
		FlowPane mainLayout = new FlowPane(Orientation.VERTICAL, 10, 20);
		mainLayout.getChildren().addAll(button, result);
		mainLayout.setAlignment(Pos.CENTER_RIGHT);
		mainLayout.setOrientation(Orientation.VERTICAL);
		mainLayout.setHgap(10);
		mainLayout.setVgap(20);
		
		Scene scene = new Scene(mainLayout);
		primaryStage.setTitle("Anonymous Class");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
