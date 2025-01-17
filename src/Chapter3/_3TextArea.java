package Chapter3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
public class _3TextArea extends Application {
@Override // Override the start method in the Application class
public void start(Stage primaryStage) {
// Declare and create a description pane
_3DescriptionPane descriptionPane = new _3DescriptionPane();
// Set title, text and image in the description pane
descriptionPane.setTitle("Canada");
String description = "The Canadian national flag ...";
descriptionPane.setImageView(new ImageView("file:/C:/Users/User/OneDrive/Documents/New folder/A. CHAI SHUN YING/SEM3/CSC3104 Advanced Programming/images.png"));
descriptionPane.setDescription(description);
// Create a scene and place it in the stage
Scene scene = new Scene(descriptionPane, 450, 200);
primaryStage.setTitle("TextAreaDemo"); // Set the stage title
primaryStage.setScene(scene); // Place the scene in the stage
primaryStage.show(); // Display the stage
}
/**
* The main method is only needed for the IDE with limited
* JavaFX support. Not needed for running from the command line.
*/
public static void main(String[] args) {
launch(args);
}
}