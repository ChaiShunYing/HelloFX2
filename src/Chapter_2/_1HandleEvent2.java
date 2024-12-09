package Chapter_2;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class _1HandleEvent2 extends Application{

	public void start(Stage primaryStage)
	{
		Button button = new Button("Click me");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@ Override
			public void handle(ActionEvent event)
			{
				System.out.println("Button clicked!");
			}
		});
		
		Scene scene = new Scene(button, 200, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			System.out.println("Button clicked!");
		}
	}
}


