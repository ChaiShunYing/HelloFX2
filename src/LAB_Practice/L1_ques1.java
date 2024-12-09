package LAB_Practice;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.geometry.Pos;

public class L1_ques1 extends Application{

	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		GridPane gp1 = new GridPane();
		gp1.setAlignment(Pos.CENTER);
		Button btReset = new Button("Reset");
		
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		btReset.setOnAction(e -> {
			gp1.getChildren().clear(); // this action will clear all children in gp1, including btReset
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					String randomNum = Integer.toString((int)(2 + ((11 - 2) * Math.random())));
					TextField tf = new TextField();
					tf.setText(randomNum);
					tf.setPrefWidth(40); // width in pixels
					tf.setPrefHeight(40); // width in pixels
					tf.setAlignment(Pos.CENTER);
					tf.setEditable(false);
//					tf.setPrefColumnCount(1); // width in character columns
					gp1.add(tf, j, i); // j = column, i = row
				}
			}
		});
		vbox.getChildren().addAll(btReset, gp1);
//		GridPane mainLayout = new GridPane();
//		mainLayout.add(btReset, 0, 0);
//		mainLayout.add(gp1, 0, 1);
		Scene scene = new Scene(vbox, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lab 1 Assignment");
		primaryStage.show();
	}
}
