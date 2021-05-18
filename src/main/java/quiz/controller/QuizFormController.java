package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import javafx.stage.Stage;
import org.tinylog.Logger;
import quiz.model.Question;
import quiz.model.QuestionModel;
import quiz.model.Quiz;

import java.io.File;
import java.io.IOException;
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
    public final String currentDir = System.getProperty("user.dir");


    /**
     * Sets the starting state of this scene.
     *
     * Reads the content of the json file into a {@code Quiz} object.
     * Then displays the questions on the {@code listView} of the scene.
     * Sets {@code addButton} and {@code saveButton} buttons to un-clickable.
     * @throws IOException if it cannot find the json file.
     */
    public void initialize() throws IOException {

        //InputStream form = getClass().getClassLoader().getResourceAsStream("custom.json");
        File form = new File(currentDir + "\\custom.json");
        if(!form.exists()){
            return;
        }
        var reader = new ObjectMapper();
        Quiz quiz = reader.readValue(form, Quiz.class);

        List<QuestionModel> modelList = quiz.getQuestions()
                .stream()
                .map(Question::toModel)
                .collect(Collectors.toList());
        listView.setItems(FXCollections.observableArrayList(modelList));
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
     * Saves the content of {@code listView} into custom.json.
     *
     * I used absolute filepath instead of relative, because I couldn't solve the problem.
     * @throws IOException if it cannot load the json file.
     */
    @FXML
    public void saveQuestion() throws Exception {
        List<Question> list = listView.getItems()
                .stream()
                .map(QuestionModel::toData)
                .collect(Collectors.toList());

        var writer = new ObjectMapper();
        writer.enable(SerializationFeature.INDENT_OUTPUT);
        writer.writeValue(new File(currentDir + "\\custom.json"), new Quiz(list));
        Logger.info("Question saved");
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
