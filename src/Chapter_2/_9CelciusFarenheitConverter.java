package Chapter_2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.*;

public class _9CelciusFarenheitConverter extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage)
	{
		Label lblFarenheit = new Label("Farenheit");
		Label lblCelcius = new Label("Celcius");
		TextField tfFarenheit = new TextField();
		tfFarenheit.setAlignment(Pos.CENTER_RIGHT);
		TextField tfCelcius = new TextField();
		tfCelcius.setAlignment(Pos.CENTER_RIGHT);
		
		GridPane gp = new GridPane();
		gp.add(lblFarenheit, 0, 0);
		gp.add(lblCelcius, 0, 1);
		gp.add(tfFarenheit, 1, 0);
		gp.add(tfCelcius, 1, 1);
		
		
		tfFarenheit.setOnAction(e -> {
			double farenheit = (Double.parseDouble(tfFarenheit.getText()));
			double celcius = (farenheit - 32) * 5 / 9;
			tfCelcius.setText(String.format("%.1f", celcius));
		});
		
		tfCelcius.setOnAction(e ->{
			double celcius = (Double.parseDouble(tfCelcius.getText()));
			double farenheit = celcius * 9 / 5 + 32;
			tfFarenheit.setText(String.format("%.1f", farenheit));
		});
		
		Scene scene = new Scene(gp);
		primaryStage.setTitle("Exercise16_04");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
}