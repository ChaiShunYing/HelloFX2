package Chapter_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class _2GridPane3 extends Application{

	private TextField tfMySentence = new TextField();
	private Button btA = new Button("A"), btB = new Button("B"), btC = new Button("C"),
				   btD = new Button("D"), btE = new Button("E"), btF = new Button("F"),
	 			   btReset = new Button("Reset");
	
	public void start(Stage stage)
	{
		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(5);
		gp.setPadding(new Insets(10));
		gp.add(new Label("MySentence:"), 0, 0);
		tfMySentence.setPrefWidth(400);
		//tfMySentence.setPrefHeight(300);
		gp.add(tfMySentence, 1, 0);
		tfMySentence.setText("Words: ");
		tfMySentence.setEditable(false);
		
		HBox buttonPane1 = new HBox(), buttonPane2 = new HBox();
		buttonPane1.getChildren().addAll(btA, btB, btC);
		buttonPane2.getChildren().addAll(btD, btE, btF);
		gp.add(buttonPane1, 0, 1);
		gp.add(btReset, 1, 1);
		gp.add(buttonPane2, 0, 2);
		
		btA.setOnAction(e -> tfMySentence.setText(getMySentence() + " C++ "));
		btB.setOnAction(e -> tfMySentence.setText(getMySentence() + " Love "));
		btC.setOnAction(e -> tfMySentence.setText(getMySentence() + " I "));
		btD.setOnAction(e -> tfMySentence.setText(getMySentence() + " OOP "));
		btE.setOnAction(e -> tfMySentence.setText(getMySentence() + " Java "));
		btF.setOnAction(e -> tfMySentence.setText(getMySentence() + " Python "));
		btReset.setOnAction(e -> tfMySentence.setText("End of Test "));
		
		Scene scene = new Scene(gp);
		stage.setTitle("MySentence Apps");
		stage.setScene(scene);
		stage.show();
		gp.requestFocus();
	}
	
	private String getMySentence()
	{
		return tfMySentence.getText();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
