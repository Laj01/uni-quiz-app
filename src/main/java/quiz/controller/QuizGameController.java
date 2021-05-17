package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import quiz.model.Question;
import quiz.model.Quiz;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This controller of the application is responsible for initiating the game itself.
 * Displays the content of the json files, handles the input from the user.
 */
public class QuizGameController {
    @FXML
    private Label questionLabel;
    @FXML
    private Button buttonA;
    @FXML
    private Button buttonB;
    @FXML
    private Button buttonC;
    @FXML
    private Button buttonD;
    @FXML
    private Label NumberOfQuestions;
    private int index;
    private int correctAnswers;
    private List<Question> questions;
    private InputStream form = getClass().getClassLoader().getResourceAsStream("default.json");

    /**
     * Reads default.json from the beginning.
     * @throws Exception from {@code initialize()} if it cannot find the json file.
     */
    @FXML
    private void loadDefaultFile() throws Exception {
        form = getClass().getClassLoader().getResourceAsStream("default.json");
        initialize();
    }

    /**
     * Reads custom.json from the beginning.
     * @throws Exception from {@code initialize()} if it cannot find the json file.
     */
    @FXML
    private void loadCustomFile() throws Exception {
        form = getClass().getClassLoader().getResourceAsStream("custom.json");
        initialize();
    }


    /**
     * Sets the starting state of the quiz.
     *
     * Reads the questions from the json and displays it on the screen with {@code showQuestion()} .
     * @throws Exception if it cannot find the json file.
     */
    @FXML
    public void initialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Quiz quiz = objectMapper.readValue(form, Quiz.class);
        questions = quiz.getQuestions();
        index = 0;
        correctAnswers = 0;
        showQuestion();
        NumberOfQuestions.setText("There are "+ questions.size() + " questions in this Quiz.");
    }

    /**
     * Sets the question on the screen.
     *
     * Reads the answers into {@code originalAnswerList},
     * then shuffles and displays them on the buttons.
     */
    @FXML
    private void showQuestion() {
        questionLabel.setText(questions.get(index).getQuestionText());

        ArrayList<String> originalAnswerList = new ArrayList<>();
        originalAnswerList.add(questions.get(index).getAnswerA());
        originalAnswerList.add(questions.get(index).getAnswerB());
        originalAnswerList.add(questions.get(index).getAnswerC());
        originalAnswerList.add(questions.get(index).getAnswerD());
        Collections.shuffle(originalAnswerList);

        buttonA.setText(originalAnswerList.get(0));
        buttonB.setText(originalAnswerList.get(1));
        buttonC.setText(originalAnswerList.get(2));
        buttonD.setText(originalAnswerList.get(3));
    }

    /**
     * Handles the user input.
     *
     * If the answer was correct, reads the next question in the json,
     * if not, stops the game with a pop-up window and starts a new quiz.
     * @param event The {@code ActionEvent}, on which this function is called.
     * @throws Exception from {@code initialize()} if it cannot find the json file.
     */
    @FXML
    private void checkForValidAnswer(ActionEvent event) throws Exception {
        Button answerButton = (Button) event.getTarget();
        correctAnswers++;
        if (questions.get(index).getAnswerA().equals(answerButton.getText()) && questions.size() >= index+2) {
            index++;
            showQuestion();
        } else {
            alert();
            loadDefaultFile();
        }
    }

    /**
     * Alert window.
     *
     * Pops up after clicking on the last answer of the quiz.
     * Displays the result to the user.
     */
    @FXML
    private void alert() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Quiz EOF");
        a.setHeaderText("The Quiz is over.\nYou reached Question No." + correctAnswers + "!\nCongratulations!");
        a.setContentText("The quiz now restarts.");
        a.showAndWait();
    }

    /**
     * Switches to the MainMenu scene.
     * @param event The {@code ActionEvent}, on which this function is called.
     * @throws IOException if it cannot find the fxml file.
     */
    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
