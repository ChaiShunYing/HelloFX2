package Chapter_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class _10TextField extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        textField.setPrefColumnCount(20); // Set preferred column count
        textField.setAlignment(Pos.CENTER); // Center align the text
        textField.setPromptText("Enter text here..."); // Set prompt text
        
        // Event handler for when the Enter key is pressed
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Text entered: " + textField.getText());
            }
        });
        
        Scene scene = new Scene(textField, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextField Example");
        primaryStage.show();
    }
}