package quiz.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private String questionText = "Test QuestionText";
    private String answerA = "Test AnswerA";
    private String answerB = "Test AnswerB";
    private String answerC = "Test AnswerC";
    private String answerD = "Test AnswerD";

    @Test
    void toModel() {
        var model = new QuestionModel();
        model.questionText.set(questionText);
        model.answerA.set(answerA);
        model.answerB.set(answerB);
        model.answerC.set(answerC);
        model.answerD.set(answerD);

        assertEquals(questionText, model.getQuestionText().getValue());
        assertEquals(answerA, model.getAnswerA().getValue());
        assertEquals(answerB, model.getAnswerB().getValue());
        assertEquals(answerC, model.getAnswerC().getValue());
        assertEquals(answerD, model.getAnswerD().getValue());
    }
}