package quiz.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

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
    private void switchToQuizGame(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizGame.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("QuizGame.fxml successfully loaded");
        } catch (IOException e) {
            Logger.error(new RuntimeException("FXML not found"), "Could not load QuizGame.fxml");
        }
    }

    /**
     * Switches to the QuizForm scene.
     * @param event The {@code ActionEvent}, on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    @FXML
    private void switchToQuizForm(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizForm.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("QuizForm.fxml successfully loaded");
        } catch (IOException e) {
            Logger.error(new RuntimeException("FXML not found"), "Could not load QuizForm.fxml");
        }
    }

    /**
     * Exits the application.
     * @param event The {@code ActionEvent}, on which this function is called.
     */
    @FXML
    private void handleExit(ActionEvent event) {
        Logger.info("Exiting the application...");
        Platform.exit();
    }
}
