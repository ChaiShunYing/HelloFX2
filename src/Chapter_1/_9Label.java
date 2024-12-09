package Chapter_1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.geometry.*;

public class _9Label extends Application{
	
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		Label lbl1 = new Label("Circle", new Circle(10, 10, 20));
		lbl1.setContentDisplay(ContentDisplay.TOP);
		lbl1.setStyle("-fx-text-fill:orange;");
		
		Rectangle r1 = new Rectangle(50, 50, 100, 100);
		r1.setStroke(Color.BLUE);
		r1.setFill(Color.YELLOW);
		
		Label lbl2 = new Label("Rectangle", r1);
		lbl2.setContentDisplay(ContentDisplay.BOTTOM);
		lbl2.setTextFill(Color.BLUE);
		
		Ellipse ellipse = new Ellipse(100, 100, 50, 25);
		Label lbl3 = new Label("Ellipse");
		lbl3.setTextFill(Color.WHITE);
		StackPane sp = new StackPane();
		sp.getChildren().addAll(ellipse, lbl3);
		
		Button button = new Button("Click", new Circle(150, 150, 100));
		button.setStyle("-fx-text-fill:white;");
		button.setStyle("-fx-border-color:purple");
		
		Pane mainLayout = new Pane();
		mainLayout.setPadding(new Insets(10));
		mainLayout.getChildren().addAll(lbl2, lbl1, sp, button);
		Scene scene = new Scene(mainLayout, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
