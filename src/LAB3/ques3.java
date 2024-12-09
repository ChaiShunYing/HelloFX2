package LAB3;
// Name: Chai Shun Ying
// Matric: 224414
//Lab information: Lab 3 Ques 2
//Date: 26/11/2024

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ques3 extends Application{
	// main method to launch JavaFX application
	public static void main(String[]args)
	{
		launch(args); // launch the JavaFX application by calling start()
	}
	
	// initialize the RADIUS and MAX_CLICK
	private static final int RADIUS = 10;
	private static final int MAX_CLICK = 20;
	private int clickCount = 0;
	private long startTime;
	
	public void start(Stage primaryStage)
	{
		Pane pane = new Pane();
		
		// create Label to show user on what should user do
		Label lblInfo = new Label("Click me and start click the circle!");
		lblInfo.setPadding(new Insets(20));
		lblInfo.setFont(new Font("Times New Roman", 16));
		lblInfo.setTextFill(Color.RED);
		
		// add info label into pane
		pane.getChildren().add(lblInfo);
		
		// create and set radius of circle
		Circle circle = new Circle(RADIUS);
		// set circle to invisible mode
		circle.setVisible(false);
		// add circle into pane
		pane.getChildren().add(circle);
		
		// set mouse click action of lblInfo
		lblInfo.setOnMouseClicked(e ->{
			// clear the lblInfo
			lblInfo.setText("");
			
			// Generate a new random circle on the pane
			randomCircle(circle, pane);
			
			// Set circle to visible mode
			circle.setVisible(true);
			
			// Set mouse click action on circle
			circle.setOnMouseClicked(e1 ->{
				// if it is the first click, record the start time
				if(clickCount == 0)
					startTime = System.currentTimeMillis();
				clickCount++;
				
				// determine whether the click count is below the maximum allowed clicks
				if(clickCount < MAX_CLICK)
				{
					// Generate a new random circle on the pane
					randomCircle(circle, pane);
				}
				else
				{
					// if the maximum clicks are reached, record the end time
					long endTime = System.currentTimeMillis();
					
					// calculate total time spent to click the circle
					long timeClick = endTime - startTime;
					
					// clear all circles from the pane
					pane.getChildren().clear();
					
					// create a label to display the result
					Label lblResult = new Label("Time spent is " + timeClick + " milliseconds");
					lblResult.setPadding(new Insets(20));
					lblResult.setFont(new Font("Times New Roman", 16));
					lblResult.setTextFill(Color.BLUE);
					
					// add the result label to the pane 
					pane.getChildren().add(lblResult);
				}
					
			});
		});
		
		// set up the scene and stage to display scene on the stage
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Exercise15_19");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// method to set random color and position of circle on the pane
	private void randomCircle(Circle circle, Pane pane)
	{
		// set random radius of circle(make sure the circle is fully visible on the pane)
		// center x -> minimum: 20, maximum: 380
		// center y -> minimum: 20, maximum: 380
		double x = RADIUS + Math.random() * (pane.getWidth() - 2 * RADIUS);
		double y = RADIUS + Math.random() * (pane.getHeight() - 2 * RADIUS);
		
		// set random color of circle
		double red = Math.random();
		double green = Math.random();
		double blue = Math.random();
		Color randomColor = Color.color(red, green, blue);
		
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setFill(randomColor);
	}
}
