import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;

/**
 * The type Main.
 *
 * @author Team 12
 * @version 1.0
 */
public class Main extends Application {


    /**
     * The constant SIZE_HEIGHT.
     */
    public static final int SIZE_HEIGHT = 350;
    /**
     * The constant SIZE_WIDTH.
     */
    public static final int SIZE_WIDTH = 275;

    /**
     * The Mediaplayer.
     */
    private MediaPlayer mediaplayer;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Trader");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:images/appIcon.png"));
        primaryStage.initStyle(StageStyle.DECORATED);
        SceneManager sm = new SceneManager(primaryStage);
        sm.goToStartScene(sm);

        Media musicFile = new Media(new File("Cell.mp3").toURI().toString());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setOnEndOfMedia(() -> mediaplayer.seek(Duration.ZERO));
        mediaplayer.play();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
