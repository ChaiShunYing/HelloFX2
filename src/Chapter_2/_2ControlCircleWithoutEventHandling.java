package Chapter_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class _2ControlCircleWithoutEventHandling extends Application
{
	public void start(Stage primaryStage)
	{
		StackPane pane = new StackPane();
		Circle circle = new Circle(50);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.PURPLE);
		pane.getChildren().add(circle);
		
		HBox hbox = new HBox();
		// spacing of hbox
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		Button btEnlarge = new Button("Enlarge");
		Button btShrink = new Button("Shrink");
		hbox.getChildren().add(btEnlarge);
		hbox.getChildren().add(btShrink);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(pane);
		bp.setBottom(hbox);
		
		Scene scene = new Scene(bp, 400, 400);
		primaryStage.setTitle("Control Circle");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		class EnlargeEventHandler implements EventHandler<ActionEvent>
		{
			@ Override
			public void handle(ActionEvent e)
			{
				pane.enlarge();
			}
		}
	}
	
	class CirclePane extends StackPane
	{
		private Circle circle = new Circle();
		public CirclePane()
		{
			getChildren().add(circle);
			circle.setStroke(Color.BEIGE);
			circle.setFill(Color.GREY);
		}
		
		public void enlarge()
		{
			circle.setRadius(circle.getRadius() * 2);
		}
		
		public void shrink()
		{
			circle.setRadius(circle.getRadius() - 2);
		}
	}
	
	
	
	public static void main(String[]args)
	{
		launch(args);
	}
}