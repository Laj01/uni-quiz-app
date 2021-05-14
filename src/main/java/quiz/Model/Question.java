package quiz.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD ;
}
