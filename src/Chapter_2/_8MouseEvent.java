package Chapter_2;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class _8MouseEvent extends Application{

	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage stage)
	{
		Pane pane = new Pane();
		Text text = new Text(20, 20, "Programming"); // 20, 20???
		pane.getChildren().addAll(text); // equivalent to .add()
		text.setOnMouseDragged(e -> {
			text.setX(e.getX());
			text.setY(e.getY());
		});
		
		Scene scene = new Scene(pane, 300, 100);
		stage.setTitle("Mouse Event Demo");
		stage.setScene(scene);
		stage.show();
	}
	
}
