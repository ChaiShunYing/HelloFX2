package Chapter_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class _10BorderPane extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		Button bt1 = new Button("Happy");
		Button bt2 = new Button("Mood");
		Button bt3 = new Button("Moshi Moshi");
		bp.setPadding(new Insets(30));
		bp.setTop(bt1);
		BorderPane.setMargin(bt3, new Insets(10));
		bp.setBottom(bt2);
		bp.setTop(bt3);
//		bp.getChildren().addAll(bt1, bt2);
		
		Scene scene = new Scene(bp, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Border Pane");
		primaryStage.show();
	}
	
}
