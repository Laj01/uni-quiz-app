package quiz.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This controller of the application is responsible for the main menu.
 */
public class MainMenuController {

    /**
     * Switches to the QuizGame scene.
     * @param event The {@code ActionEvent}, on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    @FXML
    private void switchToQuizGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizGame.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Switches to the QuizForm scene.
     * @param event The {@code ActionEvent}, on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    @FXML
    private void switchToQuizForm(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizForm.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Exits the application.
     * @param event The {@code ActionEvent}, on which this function is called.
     */
    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Exiting...");
        Platform.exit();
    }
}
