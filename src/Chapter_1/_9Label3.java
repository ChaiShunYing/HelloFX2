package Chapter_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class _9Label3 extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
	// need to add the label into HBox as if just put label, then the whole scene is Label
	// cannot see the PrefWidth of the Label
	 Label label = new Label("This is a label with preferred width.");
	 label.setStyle("-fx-background-color: lightblue;");
	 label.setPrefWidth(20);
	 HBox mainLayout = new HBox();
     mainLayout.setPadding(new Insets(10));
     mainLayout.getChildren().add(label);
     
		Scene scene = new Scene(label, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
