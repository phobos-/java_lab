package pl.edu.pwr;

import java.util.Optional;

/**
 * Created by pawel on 06.11.15.
 */
public class IdParser {

    public static Optional<Integer> parse(String id) {
        try {
            return Optional.of(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
