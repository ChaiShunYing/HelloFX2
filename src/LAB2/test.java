package LAB2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class test extends Application {
    @Override
    public void start(Stage stage) {
        String videoURL = new File("C:\\Users\\User\\OneDrive\\Documents\\New folder\\ProductVideo.mp4").toURI().toString(); // Update this to your file path

        Media media = new Media(videoURL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaPlayer.setAutoPlay(true);

        Scene scene = new Scene(mediaView, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Media Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}