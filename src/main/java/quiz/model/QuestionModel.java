package quiz.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code QuestionModel} object. It is responsible for the structure of the questions.
 * Wraps the variables into {@link SimpleStringProperty} to be easier to work with JavaFX.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
    public StringProperty questionText = new SimpleStringProperty();
    public StringProperty answerA = new SimpleStringProperty();
    public StringProperty answerB = new SimpleStringProperty();
    public StringProperty answerC = new SimpleStringProperty();
    public StringProperty answerD = new SimpleStringProperty();

    /**
     * Converts the input data into {@code Question} object.
     * @return {@link Question} object
     */
    public Question toData(){
        return new Question(questionText.get(), answerA.get(), answerB.get(), answerC.get(), answerD.get());
    }

    /**
     * For displaying the content of the {@code questionText} in the {@code QuizFormController} scene.
     * @return {@code SimpleStringProperty} of {@code questionText} as {@link String}
     */
    @Override
    public String toString(){
        return questionText.get();
    }
}
