package papa.noel;

import java.util.Arrays;
import java.util.List;

enum Strategy {
    INCOMPLETE,
    COMPLETE
}

public class RockPaperScissors {
    private static final int DRAW_POINTS = 3;
    private static final int WIN_POINTS = 6;
    private static final int ROCK_POINTS = 1;
    private static final int PAPER_SCORE = 2;
    private static final int LOSS_SCORE = 0;
    private static final int SCISSORS_POINTS = 3;
    private final List<String> inputs;

    public RockPaperScissors(String input) {
        inputs = Arrays.stream(input.split("\n")).toList();
    }

    public RockPaperScissors(List<String> input) {
        inputs = input;
    }

    public Integer getScore(Strategy strategy) {
        int score = 0;

        for (String s : inputs) {
            String input = convertToPlays(s, strategy);

            switch (input) {
                case "Rock Rock":
                    score += ROCK_POINTS + DRAW_POINTS;
                    break;
                case "Rock Paper":
                    score += PAPER_SCORE + WIN_POINTS;
                    break;
                case "Rock Scissors":
                    score += SCISSORS_POINTS + LOSS_SCORE;
                    break;
                case "Paper Rock":
                    score += ROCK_POINTS + LOSS_SCORE;
                    break;
                case "Paper Paper":
                    score += PAPER_SCORE + DRAW_POINTS;
                    break;
                case "Paper Scissors":
                    score += SCISSORS_POINTS + WIN_POINTS;
                    break;
                case "Scissors Rock":
                    score += ROCK_POINTS + WIN_POINTS;
                    break;
                case "Scissors Paper":
                    score += PAPER_SCORE + LOSS_SCORE;
                    break;
                case "Scissors Scissors":
                    score += SCISSORS_POINTS + DRAW_POINTS;
                    break;
                default:
                    return -1;
            }
        }

        return score;
    }

    private static String convertToPlays(String input, Strategy strategy) {
        input = input.replace("A", "Rock");
        input = input.replace("B", "Paper");
        input = input.replace("C", "Scissors");

        if (strategy == Strategy.INCOMPLETE) {
            input = input.replace("X", "Rock");
            input = input.replace("Y", "Paper");
            input = input.replace("Z", "Scissors");
        } else {
            String NEED_LOSS = "X";
            String NEED_DRAW = "Y";
            String NEED_WIN = "Z";

            if (input.contains(NEED_LOSS)) {
                if(input.contains("Rock")) {
                    input = input.replace(NEED_LOSS, "Scissors");
                } else if (input.contains("Paper")) {
                    input = input.replace(NEED_LOSS, "Rock");
                } else if (input.contains("Scissors")) {
                    input = input.replace(NEED_LOSS, "Paper");
                }
            } else if (input.contains(NEED_DRAW)) {
                if(input.contains("Rock")) {
                    input = input.replace(NEED_DRAW, "Rock");
                } else if (input.contains("Paper")) {
                    input = input.replace(NEED_DRAW, "Paper");
                } else if (input.contains("Scissors")) {
                    input = input.replace(NEED_DRAW, "Scissors");
                }
            } else if (input.contains(NEED_WIN)) {
                if(input.contains("Rock")) {
                    input = input.replace(NEED_WIN, "Paper");
                } else if (input.contains("Paper")) {
                    input = input.replace(NEED_WIN, "Scissors");
                } else if (input.contains("Scissors")) {
                    input = input.replace(NEED_WIN, "Rock");
                }
            }
        }

        return input;
    }
}
