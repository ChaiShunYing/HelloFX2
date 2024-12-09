package LAB1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;

public class ques1_button extends Application{
	@Override
	public void start(Stage stage)
	{
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		
		Button btn = new Button("Generate Number");
		btn.setOnAction(e -> {
			gridPane.getChildren().clear();
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					TextField tf = new TextField();
					
					int randomNum = (int)(2 + ((10 - 2) * Math.random()));
					tf.setText(Integer.toString(randomNum));
					tf.setPrefWidth(40);
					tf.setPrefHeight(40);
					tf.setAlignment(Pos.CENTER); // or tf.setStyle("-fx-alignment: center;");
					gridPane.add(tf, i, j);
				}
			}
		});
		
		// 将按钮添加到 GridPane 的上方
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
     	root.setVgap(10); // 设置按钮和网格的垂直间距
     	root.add(btn, 0, 0); // 按钮放在第一行
     	root.add(gridPane, 0, 1); // GridPane 放在按钮下方	
		
		// Place the button in the scene
		// Scene(Parent root(root nodes of Scene, control class involve Button), double width, double height)
     	Scene scene = new Scene(root, 320, 400);
     	stage.setTitle("Lab 1 Assignment");
     	stage.setScene(scene);
     	stage.show();
	}
	public static void main(String[]args)
	{
		launch(args);
	}
}
