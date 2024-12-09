package Chapter_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class _3ControlCircle extends Application {
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		VBox mainLayout = new VBox(10);
		mainLayout.setAlignment(Pos.CENTER);
		Circle c1 = new Circle(10, 10, 50);
		c1.setFill(Color.PINK);
		
		Button btnEnlarge = new Button("Enlarge");
		btnEnlarge.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e)
			{
				c1.setRadius(c1.getRadius() + 2);
			}
		});
		Button btnShrink = new Button("Shrink");
		btnShrink.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e)
			{
				if(c1.getRadius() > 2)
					c1.setRadius(c1.getRadius() - 2);
			}
		});
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btnEnlarge, btnShrink);
		mainLayout.getChildren().addAll(c1, hbox);
		
		Scene scene = new Scene(mainLayout, 400, 400);
		primaryStage.setTitle("Circle with listener");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
