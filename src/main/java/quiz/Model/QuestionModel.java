package quiz.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionModel {

    public StringProperty question = new SimpleStringProperty();
    public StringProperty answerA = new SimpleStringProperty();
    public StringProperty answerB = new SimpleStringProperty();
    public StringProperty answerC = new SimpleStringProperty();
    public StringProperty answerD = new SimpleStringProperty();
}
