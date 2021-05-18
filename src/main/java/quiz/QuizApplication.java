package quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * Quiz application.
 */
public class QuizApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            stage.setTitle("Quiz Game");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();
            Logger.info("MainMenu.fxml successfully loaded");
        } catch (IOException e) {
            Logger.error(new RuntimeException("FXML not found"), "Could not load MainMenu.fxml");
        }
    }
}
