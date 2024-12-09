package Chapter_1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

public class _8HBoxFillHeight extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	// Create two buttons with a larger height to visualize the effect of fillHeight
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        
        // Set the preferred height of buttons to make the effect more visible
        button1.setPrefHeight(100);
        button2.setPrefHeight(100);
        
        // Create an HBox layout
        HBox hbox = new HBox(10);
        
        // Set fillHeight to true to make the children fill the height of the HBox
        hbox.setFillHeight(false);
        
        // Add buttons to HBox
        hbox.getChildren().addAll(button1, button2);
        
        // Set the alignment of the HBox
        hbox.setAlignment(Pos.CENTER);
        
        // Set the HBox size
        hbox.setPrefHeight(300); // Set a large height for the HBox to see the effect of fillHeight
        
        // Create a scene
        Scene scene = new Scene(hbox, 400, 300);
        
        // Set up the stage
        primaryStage.setTitle("HBox fillHeight Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}