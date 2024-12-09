package Chapter_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.event.*;

public class _1HandlerEvent3 extends Application{

	private CirclePane circlePane = new CirclePane();
	
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		HBox hbox = new HBox(10);
		// hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		Button btnEnlarge = new Button("Enlarge");
		Button btnShrink = new Button("Shrink");
		hbox.getChildren().addAll(btnEnlarge, btnShrink);
		
		btnEnlarge.setOnAction(new EnlargeHandler());
		btnShrink.setOnAction(new ShrinkHandler());
		
		BorderPane bp = new BorderPane();
		bp.setCenter(circlePane);
		bp.setBottom(hbox);
		BorderPane.setAlignment(hbox, Pos.CENTER);
		
		Scene scene = new Scene(bp, 200, 150);
		primaryStage.setTitle("ControlCircle");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	class EnlargeHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			circlePane.enlarge();
		}
	}
	class ShrinkHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			circlePane.shrink();
		}
	}
}

class CirclePane extends StackPane 
// as we use CirclePane class to create a circle, so we need to add it inside StackPane
{
	private Circle circle = new Circle(50);
	
	public CirclePane()
	{
		getChildren().add(circle);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
	}
	
	public void enlarge()
	{
		circle.setRadius(circle.getRadius() + 2);
	}
	
	public void shrink()
	{
		circle.setRadius(circle.getRadius() > 2?
			circle.getRadius() - 2: circle.getRadius());
	}
}
