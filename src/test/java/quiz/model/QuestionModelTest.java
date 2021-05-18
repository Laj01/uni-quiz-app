package quiz.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionModelTest {

    public StringProperty questionText = new SimpleStringProperty(this, "", "Test QuestionText");
    public StringProperty answerA = new SimpleStringProperty(this, "", "Test AnswerA");
    public StringProperty answerB = new SimpleStringProperty(this, "", "Test AnswerB");
    public StringProperty answerC = new SimpleStringProperty(this, "", "Test AnswerC");
    public StringProperty answerD = new SimpleStringProperty(this, "", "Test AnswerD");

    String questionTextTestString = "Test QuestionText";
    String questionAnswerAString = "Test AnswerA";
    String questionAnswerBString = "Test AnswerB";
    String questionAnswerCString = "Test AnswerC";
    String questionAnswerDString = "Test AnswerD";

    @Test
    void toData() {
        Question TestQuestionObject = new Question(questionText.get(), answerA.get(), answerB.get(), answerC.get(), answerD.get());
        Question TestQuestionObjectNoArgs = new Question();

        assertEquals(questionTextTestString, TestQuestionObject.getQuestionText());
        assertEquals(questionAnswerAString, TestQuestionObject.getAnswerA());
        assertEquals(questionAnswerBString, TestQuestionObject.getAnswerB());
        assertEquals(questionAnswerCString, TestQuestionObject.getAnswerC());
        assertEquals(questionAnswerDString, TestQuestionObject.getAnswerD());

        assertNull(TestQuestionObjectNoArgs.getQuestionText());
    }

    @Test
    @Override
    public String toString() {
        String toStringTestString2 = questionText.get();
        assertEquals(questionTextTestString, toStringTestString2);
        return toStringTestString2;
    }
}
