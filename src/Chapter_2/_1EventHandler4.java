package Chapter_2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class _1EventHandler4 extends Application{
	private Circle circle = new Circle(50);
	
	
	public static void main(String[]args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		circle.setFill(Color.BLUE);
		
		Button btnColor = new Button("Change color");
		btnColor.setOnAction((ActionEvent e) -> {
			if(circle.getFill() == Color.BLUE)
				circle.setFill(Color.GREEN);
			else
				circle.setFill(Color.BLUE);
		});
	
		StackPane sp = new StackPane();
		sp.getChildren().add(circle);
		sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.PRIMARY)
					circle.setRadius(circle.getRadius() + 5);
				else if(e.getButton() == MouseButton.SECONDARY)
				{
					if(circle.getRadius() > 10)
						circle.setRadius(circle.getRadius() - 5);
				}
			}
		});
		
		sp.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.A)
				circle.setRadius(circle.getRadius() + 5);
			else if(e.getCode() == KeyCode.D)
				circle.setRadius(circle.getRadius() > 10 ? circle.getRadius() - 5: circle.getRadius());
		});
		
		sp.setFocusTraversable(true); // can receive key events
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(sp);
		borderPane.setBottom(btnColor);
		BorderPane.setAlignment(btnColor, Pos.CENTER);
		
		Scene scene = new Scene(borderPane, 400, 300);
		primaryStage.setTitle("Listener Example");
		primaryStage.setScene(scene);
		primaryStage.show();
        
		sp.requestFocus();
	}
}
