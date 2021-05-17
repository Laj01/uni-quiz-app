package quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code Question} object. It is responsible for the content of the questions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    /**
     * Generates a new {@link QuestionModel}, that contains the values of the question and the four answers.
     * By default, {@code answerA} is always the right answer.
     * @return {@link QuestionModel} object from the given data.
     */
    public QuestionModel toModel(){
        var model = new QuestionModel();
        model.questionText.set(questionText);
        model.answerA.set(answerA);
        model.answerB.set(answerB);
        model.answerC.set(answerC);
        model.answerD.set(answerD);
        return model;
    }
}
