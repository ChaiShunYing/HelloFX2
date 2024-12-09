package LAB4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MatrixDisplayListView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        int size = 2000; // Example size
        double[][] matrix = generateMatrix(size, size);

        // ListView to display the matrix
        ListView<String> listView = new ListView<>();
        
        // Add matrix rows as strings to the ListView
        for (int i = 0; i < size; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < size; j++) {
                row.append(String.format("%.2f", matrix[i][j])).append(" ");
            }
            listView.getItems().add(row.toString());
        }

        // Layout the UI
        StackPane root = new StackPane();
        root.getChildren().add(listView);

        // Set up the scene
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Matrix in ListView");
        stage.setScene(scene);
        stage.show();
    }

    // Generate a random matrix (for demonstration purposes)
    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random(); // Random values for matrix
            }
        }
        return matrix;
    }
}
