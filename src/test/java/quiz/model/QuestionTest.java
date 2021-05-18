package quiz.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private String testQuestionText = "Test QuestionText";
    private String testAnswerA = "Test AnswerA";
    private String testAnswerB = "Test AnswerB";
    private String testAnswerC = "Test AnswerC";
    private String testAnswerD = "Test AnswerD";

    @Test
    void toModel() {
        var model = new QuestionModel();
        model.questionText.set(testQuestionText);
        model.answerA.set(testAnswerA);
        model.answerB.set(testAnswerB);
        model.answerC.set(testAnswerC);
        model.answerD.set(testAnswerD);

        assertEquals(testQuestionText, model.getQuestionText().getValue());
        assertEquals(testAnswerA, model.getAnswerA().getValue());
        assertEquals(testAnswerB, model.getAnswerB().getValue());
        assertEquals(testAnswerC, model.getAnswerC().getValue());
        assertEquals(testAnswerD, model.getAnswerD().getValue());

        assertFalse(model.getQuestionText().getValue().isEmpty());
    }
}