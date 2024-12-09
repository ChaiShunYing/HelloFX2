package Chapter_1;
// import scene represents content of a window
// import scene.control: elements users interact with, ex: Button, Label, TextField
// import scene.layout: layout containers to arrange and manage the position of UI controls within a scene, ex: VBox, HBox, GridPane, BorderPane
// import stage: represents window (top-level container)

import javafx.application.Application;  
import javafx.geometry.Insets; // 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class _2GridPane extends Application{
	public void start(Stage primaryStage)
	{
		// 边框窗格 (divides the screen into top, bottom, left, right, center)
		// setTop(Node), setBottom(Node), setLeft(Node), setRight(Node), setCenter(Node)
		BorderPane pane = new BorderPane(); 
		GridPane grid = new GridPane();
		
		Label lblFirstName = new Label("First Name");
		Label lblLastName = new Label("Last Name");
		TextField txtFirstName = new TextField();
		TextField txtLastName = new TextField();
		
		Button btSave = new Button("Save");
		Button btCancel = new Button("Cancel");
		
		// create ButtonBar to hold buttons (makes your layout and alignment easy)
		// ButtonBar is 可有可无 cuz still can align by programmers to place the two buttons one by one
		// *ex: grid.add(btSave, 0, 2, 1, 1);
		// *ex: grid.add(btCancel, 1, 2, 1, 1);
		ButtonBar btBar = new ButtonBar(); 
		
		// columnIndex, rowIndex, columnSpan, rowSpan (span:占据)
		grid.add(lblFirstName, 0, 0, 1, 1);
		grid.add(txtFirstName, 1, 0, 1, 1);
		grid.add(lblLastName, 0, 1, 1, 1);
		grid.add(txtLastName, 1, 1, 1, 1);
		grid.add(btBar, 0, 2, 2, 1); // first column, third row, use 2 columns, use 1 row
		// add buttons to the ButtonBar
		btBar.getButtons().addAll(btSave, btCancel); 
		
		// make sure the GridPane can be properly displayed in the center region
		// center is the main content area, so must can be seen
		pane.setCenter(grid);
		
		// set column and row gap
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		// Create a scene and place it in stage
		primaryStage.setTitle("Grid Pane");
		primaryStage.setScene(new Scene(pane, 700, 700));
		primaryStage.show();
	}
	public static void main(String[]args)
	{
		launch(args);
	}
}

