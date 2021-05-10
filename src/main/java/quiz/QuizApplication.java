package quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));
        stage.setTitle("Quiz Game");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();

    }
}