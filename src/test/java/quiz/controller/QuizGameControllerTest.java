package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;
import quiz.model.Question;
import quiz.model.Quiz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizGameControllerTest {
    private List<Question> questions;
    private InputStream form = getClass().getClassLoader().getResourceAsStream("default.json");


    @Test
    void initialize() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Quiz quiz = objectMapper.readValue(form, Quiz.class);
            assertEquals("Which one is Test 1?", quiz.getQuestions().get(0).getQuestionText());
            questions = quiz.getQuestions();
            assertEquals("Which one is Test 1?", questions.get(0).getQuestionText());
        } catch (IOException e) {
            assertNull(form);
        }

    }
}
