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
import quiz.model.Question;
import quiz.model.QuestionModel;
import quiz.model.Quiz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QuizFormController {

    @FXML
    private ListView <QuestionModel> listView;
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


    public void initialize() throws Exception{
        File file = new File("src/main/resources/custom.json");
        if(!file.exists()){
            return;
        }

        addButton.setDisable(true);
        saveButton.setDisable(true);

        var reader = new ObjectMapper();
        Quiz quiz = reader.readValue(file, Quiz.class);

        List<QuestionModel> modelList = quiz.getQuestions()
                .stream()
                .map(Question::toModel)
                .collect(Collectors.toList());

        listView.setItems(FXCollections.observableArrayList(modelList));

    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void addQuestion(){
        QuestionModel question = new QuestionModel();
        question.questionText.setValue(questionArea.getText());
        question.answerA.setValue(answerAField.getText());
        question.answerB.setValue(answerBField.getText());
        question.answerC.setValue(answerCField.getText());
        question.answerD.setValue(answerDField.getText());

        listView.getItems().add(question);
    }

    @FXML
    public void saveQuestion() throws IOException {
        List<Question> list = listView.getItems()
                .stream()
                .map(QuestionModel::toData)
                .collect(Collectors.toList());

        var writer = new ObjectMapper();
        writer.enable(SerializationFeature.INDENT_OUTPUT);
        writer.writeValue(new File("src/main/resources/custom.json"), new Quiz(list));
    }

    @FXML
    public void editQuestion(){
        QuestionModel question = listView.getSelectionModel().getSelectedItem();
        if (question == null){
            return;
        }

        if(prevSelectedQuestion != null){
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

    @FXML
    public void handleKeyReleased(){
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
