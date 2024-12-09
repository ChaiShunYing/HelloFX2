package Chapter_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class _9Label2 extends Application{
		public static void main(String[]args)
		{
			launch(args);
		}
		public void start(Stage primaryStage)
		{
			Circle circle = new Circle(10, 100, 20);
//			Label lbl1 = new Label("Circle", circle);
//			lbl1.setContentDisplay(ContentDisplay.TOP);
//			lbl1.setStyle("-fx-text-fill:orange;");
			Pane mainLayout = new Pane();
//			mainLayout.setPadding(new Insets(10));
			mainLayout.getChildren().add(circle);
			Scene scene = new Scene(mainLayout, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

