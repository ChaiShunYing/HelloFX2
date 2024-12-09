package Chapter_1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class _6PrefWidthHeight_VS_Scene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Set the title of the Stage
            stage.setTitle("PrefWidth/Height vs Scene Dimensions");

            // Create an HBox with three components: a Label, a TextField, and a Button
            HBox hbox = new HBox(10); // 10px spacing between components
            hbox.setAlignment(Pos.CENTER);
            
            // Set preferred width and height for the HBox
            hbox.setPrefWidth(300);  // Preferred width for the HBox
            hbox.setPrefHeight(10); // Preferred height for the HBox

            // Create UI elements to add to the HBox
            Label label = new Label("Enter Name:");
            TextField textField = new TextField();
            Button button = new Button("Submit");

            // Add elements to the HBox
            hbox.getChildren().addAll(label, textField, button);

            // Create a BorderPane and place the HBox in the center
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(hbox);

            // Create a Scene with specific dimensions
            Scene scene = new Scene(borderPane, 60, 40); // Scene width and height

            // Set the scene to the stage and show it
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
