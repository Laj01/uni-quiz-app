package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quiz.model.Question;
import quiz.model.QuestionModel;
import quiz.model.Quiz;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomQuizGameController {

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
    private  String questionDisplay;
    private int index = 0;

    public void initialize() throws Exception{

        File file = new File("src/main/resources/custom.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Quiz quiz= objectMapper.readValue(file, Quiz.class);
        List<Question> questions= quiz.getQuestions();

        questionLabel.setText(questions.get(index).getQuestionText());
        buttonA.setText(questions.get(index).getAnswerA());
        buttonB.setText(questions.get(index).getAnswerB());
        buttonC.setText(questions.get(index).getAnswerC());
        buttonD.setText(questions.get(index).getAnswerD());
    }

    @FXML
    public void checkForValid(){

    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }


}
