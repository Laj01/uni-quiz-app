package quiz.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {

    public StringProperty questionText = new SimpleStringProperty();
    public StringProperty answerA = new SimpleStringProperty();
    public StringProperty answerB = new SimpleStringProperty();
    public StringProperty answerC = new SimpleStringProperty();
    public StringProperty answerD = new SimpleStringProperty();

    public Question toData(){

        return new Question(questionText.get(), answerA.get(), answerB.get(), answerC.get(), answerC.get());
    }

    @Override
    public String toString(){

        return questionText.get();
    }
}
