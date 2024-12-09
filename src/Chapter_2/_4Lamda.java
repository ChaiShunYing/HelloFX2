package Chapter_2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class _4Lamda extends Application{
	
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage)
	{	
		Button button = new Button("Click me");
		Label consoleScreen = new Label();
		// 4 situations on lamda:
		// 1. setOnAction((ActionEvent e) -> {}); not necessary need as javafx will implicitly use ActionEvent class
		// 2. setOnAction((e) -> {}); not necessary since only one parameter is needed
		// 3. setOnAction(e -> {}); 
		// 4. setOnAction(e -> ); only use for action in 1 line
		// button.setOnAction((ActionEvent e) -> {consoleScreen.setText("Button clicked!");}); Label is not a text, must use setText
	    // button.setOnAction((e) -> {consoleScreen.setText("Button clicked!");});
		// button.setOnAction(e -> {consoleScreen.setText("Button clicked");});
		button.setOnAction(e -> consoleScreen.setText("Button clicked"));

		VBox mainLayout = new VBox(10);
		mainLayout.setPadding(new Insets(20));
		mainLayout.getChildren().addAll(button, consoleScreen);
		
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.setTitle("lamda example");
		primaryStage.show();
	}

}
