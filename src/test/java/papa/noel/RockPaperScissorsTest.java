package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

    @Test
    void day_2_with_first_round_win() {
        // Given
        String input = """
                A Y
                """;

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);

        // Then
        assertEquals(rockPaperScissors.getScore(Strategy.INCOMPLETE), 8);
    }

    @Test
    void day_2_with_first_round_win_with_a_loss() {
        // Given
        String input = """
                B X
                """;

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);

        // Then
        assertEquals(rockPaperScissors.getScore(Strategy.INCOMPLETE), 1);
    }

    @Test
    void day_2_with_first_round_draw() {
        // Given
        String input = """
                C Z
                """;

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);

        // Then
        assertEquals(rockPaperScissors.getScore(Strategy.INCOMPLETE), 6);
    }

    @Test
    void day_2_with_multiple_rounds() {
        // Given
        String input = """
                A Y
                B X
                C Z
                """;

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);

        // Then
        assertEquals(rockPaperScissors.getScore(Strategy.INCOMPLETE), 15);
    }

    @Test
    void day_2_first_challenge() throws IOException {
        // Givenr
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day2"));

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(strings);

        // Then
        assertEquals(12740, rockPaperScissors.getScore(Strategy.INCOMPLETE));
    }


    @Test
    void day_2_with_real_explainations() {
        // Given
        String input = """
                A Y
                B X
                C Z
                """;

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);

        // Then
        assertEquals( 12, rockPaperScissors.getScore(Strategy.COMPLETE));
    }

    @Test
    void day_2_challenge_with_real_explainations() throws IOException {
        // Given
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day2"));

        // When
        RockPaperScissors rockPaperScissors = new RockPaperScissors(strings);

        // Then
        assertEquals( 11980, rockPaperScissors.getScore(Strategy.COMPLETE));
    }

}
