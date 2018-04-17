import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class BackgroundMusic extends Application implements Runnable {
    private MediaPlayer mediaPlayer;


    /*
        This is called when the JavaFX Application is launched.
        It creates our audio data and starts our thread
     */
    @Override
    public void start(Stage primaryStage) {
        String path = getParameters().getRaw().get(0);
        boolean loop = getParameters().getRaw().get(1).equals("true");

        Media hit = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);

        if (loop) {
            double stopTime = Double.valueOf(getParameters().getRaw().get(2));
            mediaPlayer.setStartTime(Duration.seconds(0));
            mediaPlayer.setStopTime(Duration.seconds(stopTime));
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        Thread t = new Thread(this);
        t.start();
    }

    /*
        Needed for JavaFX's Application to run properly
     */
    public void init() { }

    /*
        Run is called when our Thread's start() is called
     */
    @Override
    public void run() {
        mediaPlayer.play();
    }
}
