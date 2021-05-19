package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.tinylog.Logger;
import quiz.model.Question;
import quiz.model.QuestionModel;
import quiz.model.Quiz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller of the application is responsible for creating a custom quiz.
 *
 * The custom.json is built from the user input.
 */
public class QuizFormController {

    @FXML
    private ListView<QuestionModel> listView;
    @FXML
    private TextArea questionArea;
    @FXML
    private TextField answerAField;
    @FXML
    private TextField answerBField;
    @FXML
    private TextField answerCField;
    @FXML
    private TextField answerDField;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    private QuestionModel prevSelectedQuestion;
    private String filePath;


    /**
     * Sets the starting state of this scene.
     * * Sets {@code addButton} and {@code saveButton} buttons to un-clickable.
     */
    public void initialize() {
        addButton.setDisable(true);
        saveButton.setDisable(true);
    }

    /**
     * Switches to the MainMenu scene.
     * @param event The {@code ActionEvent}, on which this function is called.
     */
    @FXML
    private void switchToMainMenu(final ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("MainMenu.fxml loaded");
        } catch (IOException e) {
            Logger.error(new RuntimeException("FXML not found"), "Could not load QuizForm.fxml");
        }
    }

    /**
     * Adds the user input from the {@code TextArea} and 4 {@code TextField} into a {@code QuestionModel}.
     * Then displays the added {@code questionText} on the {@code listView}.
     */
    @FXML
    public void addQuestion() {
        QuestionModel question = new QuestionModel();
        question.questionText.setValue(questionArea.getText());
        question.answerA.setValue(answerAField.getText());
        question.answerB.setValue(answerBField.getText());
        question.answerC.setValue(answerCField.getText());
        question.answerD.setValue(answerDField.getText());

        listView.getItems().add(question);
        Logger.info("Question added to the listView");
    }

    /**
     * Saves the file to the previously choosen location
     * @param filePath on the current computer.
     * @throws IOException in case the file save was not successful. {@code saveQuestion()} will catch it.
     */
    public void saveAs(String filePath) throws IOException {
        List<Question> list = listView.getItems()
                .stream()
                .map(QuestionModel::toData)
                .collect(Collectors.toList());

        var writer = new ObjectMapper();
        writer.enable(SerializationFeature.INDENT_OUTPUT);
        writer.writeValue(new File(filePath + ".json"), new Quiz(list));
        Logger.info("Question saved");
    }

    /**
     * Opens a {@code Filechooser} window for the user to choose the library, where the
     * file will be saved.     *
     *
     * I used absolute filepath instead of relative, because I couldn't solve the problem.
     */
    @FXML
    public void saveQuestion() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Custom Quiz");
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            Logger.info("Saving file {}", file);
            try {
                saveAs(file.getPath());
                filePath = file.getPath();
            } catch (IOException e) {
                Logger.error(e, "Failed to save file");
            }
        }
    }

    /**
     * Reads the content of {@code QuestionModel} back to the {@code TextArea} and {@code TextField}.
     * Making them modifiable again in the process.
     */
    @FXML
    public void editQuestion() {
        QuestionModel question = listView.getSelectionModel().getSelectedItem();
        if (question == null) {
            return;
        }

        if (prevSelectedQuestion != null) {
            prevSelectedQuestion.questionText.unbind();
            prevSelectedQuestion.answerA.unbind();
            prevSelectedQuestion.answerB.unbind();
            prevSelectedQuestion.answerC.unbind();
            prevSelectedQuestion.answerD.unbind();
        }
        questionArea.setText(question.questionText.getValue());
        answerAField.setText(question.answerA.getValue());
        answerBField.setText(question.answerB.getValue());
        answerCField.setText(question.answerC.getValue());
        answerDField.setText(question.answerD.getValue());

        question.questionText.bind(questionArea.textProperty());
        question.answerA.bind(answerAField.textProperty());
        question.answerB.bind(answerBField.textProperty());
        question.answerC.bind(answerCField.textProperty());
        question.answerD.bind(answerDField.textProperty());
        prevSelectedQuestion = question;
    }

    /**
     * Monitoring the {@code TextArea} and 4 {@code TextField} for content.
     * Keeps the {@code addButton} and {@code saveButton} un-clickable until all the
     * areas are filled with text.
     */
    @FXML
    public void handleKeyReleased() {
        String text1 = questionArea.getText().trim();
        String text2 = answerAField.getText().trim();
        String text3 = answerBField.getText().trim();
        String text4 = answerCField.getText().trim();
        String text5 = answerDField.getText().trim();

        boolean disableButtons = text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty() || text5.isEmpty();
        addButton.setDisable(disableButtons);
        saveButton.setDisable(disableButtons);
    }
}
