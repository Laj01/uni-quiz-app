package quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD ;

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
