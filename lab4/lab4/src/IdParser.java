import java.util.Optional;

public class IdParser {

    public static Optional<Integer> parse(String id) {
        try {
            return Optional.of(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
