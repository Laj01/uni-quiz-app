package quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    Question TestQuestion1 = new Question();
    Question TestQuestion2 = new Question();
    private Quiz quiz;

    @Test
    @BeforeEach
    void setUp(){
        this.quiz = new Quiz();
        quiz.setQuestions(Arrays.asList(TestQuestion1,TestQuestion2));
    }

    @Disabled
    @Test
    void getQuestions() {
    }

    @Test
    void setQuestions() {
        assertFalse(quiz.getQuestions().isEmpty());
    }
}