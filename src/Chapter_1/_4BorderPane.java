package Chapter_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class _4BorderPane extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	public void start(Stage stage)
	{
		try
		{
			stage.setTitle("Border Pane");
			
			Label label_center = new Label("this is BorderPane center");
			//Label label_top = new Label("this is BorderPane top");
			//Label label_bottom = new Label("this is BorderPane bottom");
			Label label_left = new Label("this is BorderPane left");
			Label label_right = new Label("this is BorderPane right");
			
			// center, top, right, bottom, left
			BorderPane border_pane = new BorderPane();
			
			border_pane.setLeft(label_left);
			border_pane.setRight(label_right);
			border_pane.setCenter(label_center);
			
//			BorderPane.setAlignment(label_top, Pos.CENTER);
//			BorderPane.setAlignment(label_bottom, Pos.CENTER);
//			BorderPane.setAlignment(label_left, Pos.CENTER);
//			BorderPane.setAlignment(label_right, Pos.CENTER);
			Scene scene = new Scene(border_pane, 600, 400);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
