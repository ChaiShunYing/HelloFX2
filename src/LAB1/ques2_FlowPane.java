package LAB1;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
public class ques2_FlowPane extends Application{

	public void start(Stage stage)
	{
		FlowPane fp = new FlowPane();
		stage.setScene(null);
		stage.setTitle("BMI Calculator");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);

	}

}
