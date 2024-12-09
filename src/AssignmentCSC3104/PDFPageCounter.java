package AssignmentCSC3104;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PDFPageCounter extends Application {

    @Override
    public void start(Stage primaryStage) {
        // UI Components
        Button uploadButton = new Button("Upload File");
        Label resultLabel = new Label("No file selected.");

        // FileChooser for selecting files of any type
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Upload button action
        uploadButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                String filePath = file.getAbsolutePath();
                String mimeType = getMimeType(file);

                resultLabel.setText("File uploaded: " + file.getName());

                // Process based on MIME type or file extension
                try {
                    if (mimeType != null) {
                        if (mimeType.equals("application/pdf")) {
                            processPdf(file);
                        } else if (mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                            processWord(file);
                        } else if (mimeType.startsWith("image/")) {
                            processImage(file);
                        } else if (mimeType.equals("text/plain")) {
                            processTextFile(file);
                        } else {
                            resultLabel.setText("Unsupported file type: " + mimeType);
                        }
                    } else {
                        resultLabel.setText("Could not determine file type.");
                    }
                } catch (IOException ex) {
                    resultLabel.setText("Error processing file.");
                }
            }
        });

        // Layout
        VBox root = new VBox(10, uploadButton, resultLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene and Stage setup
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Multi File Uploader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to get MIME type of the file
    private String getMimeType(File file) {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Process PDF file (PDFBox)
    private void processPdf(File file) {
        System.out.println("Processing PDF file: " + file.getName());
        // Here you would use PDFBox or other methods to process the PDF
        // Example: count pages, extract text, etc.
    }

    // Process Word file (Apache POI)
    private void processWord(File file) {
        System.out.println("Processing Word document: " + file.getName());
        // Use Apache POI to read the Word file
        // Example: Extract text, count paragraphs, etc.
    }

    // Process Image file (ImageIO)
    private void processImage(File file) {
        System.out.println("Processing image file: " + file.getName());
        // Use ImageIO or another library to process the image
        // Example: Load, display, or manipulate the image
    }

    // Process Text file (Text Files)
    private void processTextFile(File file) throws IOException {
        System.out.println("Processing text file: " + file.getName());
        // Here you can read the file and do something with the text
        // Example: Display file contents
    }

    public static void main(String[] args) {
        launch(args);
    }
}