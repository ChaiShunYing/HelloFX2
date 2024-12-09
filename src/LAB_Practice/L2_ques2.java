package LAB_Practice;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.geometry.*;


public class L2_ques2 extends Application{
	public static void main(String[]args)
	{
		launch(args);
	}
	TextField nameTf, weightTf, heightTf, nameTR, classificationTR, riskTR, BMITR;
	Label wrongText;
	public void start(Stage primaryStage)
	{
		Label nameLb = new Label("Name:");
		Label weightLb = new Label("Weight(kg):");
		Label heightLb = new Label("Height(m):");
		
		nameTf = new TextField();
		nameTf.setPromptText("e.g. Chai Shun Ying");
		nameTf.setStyle("-fx-background-color: black;");
		
		weightTf = new TextField();
		weightTf.setPromptText("e.g. 45");
		
		heightTf = new TextField();
		heightTf.setPromptText("e.g. 1.5");
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setStyle("-fx-background-color: pink;");
		
		gp.add(nameLb, 0, 0);
		gp.add(weightLb, 0, 1);
		gp.add(heightLb, 0, 2);
		gp.add(nameTf, 1, 0);
		gp.add(weightTf, 1, 1);
		gp.add(heightTf, 1, 2);
		
		Label nameLR = new Label("Name:");
		nameLR.setTextFill(Color.WHITE);
		Label BMILR = new Label("BMI: ");
		Label classificationLR = new Label("Classification");
		Label riskLR = new Label("Risk: ");
		
		nameTR = new TextField();
		BMITR = new TextField();
		classificationTR = new TextField();
		riskTR = new TextField();
		
		nameTR.setEditable(false);
		BMITR.setEditable(false);
		classificationTR.setEditable(false);
		riskTR.setEditable(false);
		
		gp.add(nameLR, 0, 4);
		gp.add(BMILR, 0, 5);
		gp.add(classificationLR, 0, 6);
		gp.add(riskLR, 0, 7);
		gp.add(nameTR, 1, 4);
		gp.add(BMITR, 1, 5);
		gp.add(classificationTR, 1, 6);
		gp.add(riskTR, 1, 7);
		
		Button btnCalculate = new Button("Calculate");
		btnCalculate.setOnAction(new ButtonCalcHandler());
		Button btnClear = new Button("Clear");
		btnClear.setOnAction(new ButtonClearHandler());
		gp.add(btnCalculate, 0, 3);
		gp.add(btnClear, 1, 3);
		
		wrongText = new Label();
		VBox pl = new VBox(20);
		pl.getChildren().addAll(gp, wrongText);
		
		Scene scene = new Scene(pl, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	class ButtonCalcHandler implements EventHandler<ActionEvent>
	{	
		public void handle(ActionEvent e)
		{
			try {
			double weight = Double.parseDouble(weightTf.getText());
			double height = Double.parseDouble(heightTf.getText());
			double BMI = weight / (height * height);
			BMITR.setText(String.format("%.2f", BMI));
			nameTR.setText(nameTf.getText());
				
			if(BMI < 18.5)
			{
				classificationTR.setText("Underweight");
				riskTR.setText("Increased");
			}
			else if(BMI >= 18.5 && BMI <= 24.9)
			{
				classificationTR.setText("Normal Weight");
				riskTR.setText("Least");
			}
			else if(BMI >= 25.0 && BMI <= 29.9)
			{
				classificationTR.setText("Overweight");
				riskTR.setText("Increased");
			}
			else if(BMI >= 30.0 && BMI <= 34.9)
			{
				classificationTR.setText("Obese class I");
				riskTR.setText("High");
			}
			else if(BMI >= 35.0 && BMI <= 39.9)
			{
				classificationTR.setText("Obese clas II");
				riskTR.setText("Very high");
			}
			else if(BMI >= 40.0)
			{
				classificationTR.setText("Obese class III");
				riskTR.setText("Extremely high");
			}
			
			wrongText.setText("");
			}
			catch(NumberFormatException nfe)
			{
				wrongText.setText("Wrong");
			}
		}	
	}
	class ButtonClearHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			nameTf.clear();
			weightTf.clear();
			heightTf.clear();
			
			nameTR.clear();
			BMITR.clear();
			classificationTR.clear();
			riskTR.clear();
			wrongText.setText("");
		}
	}
		

		
	
}
