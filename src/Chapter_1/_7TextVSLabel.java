package Chapter_1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class _7TextVSLabel extends Application {

    @Override
    public void start(Stage stage) {
        // Creating Text for decorative, stylized text
        Text titleText = new Text("JavaFX Text Example");
        titleText.setFont(new Font("Arial", 24)); // Set font and size
        titleText.setFill(Color.DARKBLUE); // Set color
        titleText.setUnderline(true); // Add underline for styling

        // Creating Label for contextual information
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        nameLabel.setFont(new Font("Verdana", 16));
        ageLabel.setFont(new Font("Verdana", 16));

        // Adding more styled Text examples
        Text styledText = new Text("This text has custom styling.");
        styledText.setFont(new Font("Courier New", 18));
        styledText.setFill(Color.INDIGO);
        styledText.setRotate(10); // Rotating text slightly

        // Adding Labels for user interaction context
        Label hintLabel = new Label("Hover over buttons for hints.");
        hintLabel.setFont(new Font("Verdana", 14));
        hintLabel.setStyle("-fx-text-fill: darkred;"); // Set color using CSS

        // Arrange components in a VBox layout
        VBox vbox = new VBox(10, titleText, nameLabel, ageLabel, styledText, hintLabel);
        vbox.setPadding(new Insets(15));
        
        // Setting up the scene and stage
        Scene scene = new Scene(vbox, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Text vs Label Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}