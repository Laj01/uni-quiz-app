package quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Wrapper class for a list of {@code Question} objects.
 * It is meant to make the (de-)serialization easier.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz {
    private List<Question> questions;
}
