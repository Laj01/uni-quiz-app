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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    private int index;
    private List<Question> questions;
    private File file = new File("src/main/resources/default.json");


    @FXML
    private void loadDefaultFile() throws Exception {
        file = new File("src/main/resources/default.json");
        initialize();
    }

    @FXML
    private void loadCustomFile() throws Exception {
        file = new File("src/main/resources/custom.json");
        initialize();
    }


    @FXML
    public void initialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Quiz quiz = objectMapper.readValue(file, Quiz.class);
        questions = quiz.getQuestions();
        index = 0;
        showQuestion();
    }


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

    @FXML
    private void checkForValidAnswer(ActionEvent event) throws Exception {
        Button answerButton = (Button) event.getTarget();
        if (questions.get(index).getAnswerA().equals(answerButton.getText()) && questions.size() >= index+2) {
            index++;
            showQuestion();
        } else {
            alert();
            initialize();
        }
    }

    @FXML
    private void alert() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Quiz EOF");
        a.setHeaderText("The Quiz is now over.");
        a.setContentText("You answered " + index + " questions correctly. \nThe quiz now restarts.");
        a.showAndWait();
    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
