package Chapter_2;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.event.*;

public class _7InnerClass extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	
	Label result;
	public void start(Stage primaryStage)
	{
		Button button = new Button("Click me");
		result = new Label();
		
		button.setOnAction(new ButtonClickHandler());
		
		GridPane mainLayout = new GridPane();
		mainLayout.setHgap(10);
		mainLayout.setVgap(20);
		mainLayout.setPadding(new Insets(20));
		mainLayout.setAlignment(Pos.CENTER);
		
		mainLayout.add(button, 0, 0);
		mainLayout.add(result, 0, 1);
		
        
		GridPane.setHalignment(button, HPos.RIGHT);
		
		mainLayout.addRow(1, new Button("Button 3"), new Label("Label 3"));  
        mainLayout.addRow(2, new Button("Button 4"), new Label("Label 4"));
        mainLayout.setGridLinesVisible(false);
		System.out.println(GridPane.getRowIndex(result));
		
		
		Scene scene = new Scene(mainLayout, 400, 400);
		primaryStage.setTitle("Inner Class");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	class ButtonClickHandler implements EventHandler<ActionEvent>
	{
		
		public void handle(ActionEvent e)
		{
			result.setText("Button click using Inner Class!");
		}
	}
}
