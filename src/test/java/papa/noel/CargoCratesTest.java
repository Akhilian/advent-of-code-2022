package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CargoCratesTest {

    @Test
    void test_linked_list() {
        // Given
        LinkedList<String> list = new LinkedList<>();

        // When
        list.add("A");
        list.add("B");

        // Then
        assertThat(list.getLast()).isEqualTo("B");
    }

    @Test
    void initialize_the_cargo_crate_with_stacks() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);

        // Then
        assertThat(cargoCrates.getStacksCount()).isEqualTo(3);
    }

    @Test
    void initialize_the_cargo_crate_with_more_stacks() {
        // Given
        List<String> input = List.of("""
                            [J]             [B] [W]
                            [T]     [W] [F] [R] [Z]
                        [Q] [M]     [J] [R] [W] [H]
                    [F] [L] [P]     [R] [N] [Z] [G]
                [F] [M] [S] [Q]     [M] [P] [S] [C]
                [L] [V] [R] [V] [W] [P] [C] [P] [J]
                [M] [Z] [V] [S] [S] [V] [Q] [H] [M]
                [W] [B] [H] [F] [L] [F] [J] [V] [B]
                 1   2   3   4   5   6   7   8   9\s
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);

        // Then
        assertThat(cargoCrates.getStacksCount()).isEqualTo(9);
    }

    @Test
    void initialize_the_cargo_crate() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s

                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);

        // Then
        assertThat(cargoCrates.getTopOf(1)).isEqualTo("N");
        assertThat(cargoCrates.getTopOf(2)).isEqualTo("D");
        assertThat(cargoCrates.getTopOf(3)).isEqualTo("P");
    }

    @Test
    void with_first_movement() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s

                move 1 from 2 to 1
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getTopOf(1)).isEqualTo("D");
        assertThat(cargoCrates.getTopOf(2)).isEqualTo("C");
        assertThat(cargoCrates.getTopOf(3)).isEqualTo("P");
    }

    @Test
    void with_movement_with_multiple_crate_moving_at_once() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s

                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9001);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getTopOf(1)).isEqualTo("M");
        assertThat(cargoCrates.getTopOf(2)).isEqualTo("C");
        assertThat(cargoCrates.getTopOf(3)).isEqualTo("D");
    }

    @Test
    void with_multiple_stacks() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s

                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getTopOf(1)).isEqualTo("C");
        assertThat(cargoCrates.getTopOf(2)).isEqualTo("M");
        assertThat(cargoCrates.getTopOf(3)).isEqualTo("Z");
    }

    @Test
    void with_multiple_stacks_top_of_crates() {
        // Given
        List<String> input = List.of("""
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s

                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """.split("\n"));


        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getAllTop()).isEqualTo("CMZ");
    }

    @Test
    void day_5_first_challenge() throws IOException {
        // Given
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/input_day5"));

        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9000);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getAllTop()).isEqualTo("VRWBSFZWM");
    }

    @Test
    void day_5_second_challenge() throws IOException {
        // Given
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/input_day5"));

        // When
        CargoCrates cargoCrates = new CargoCrates(input, Mover.CrateMover_9001);
        cargoCrates.moveCrates();

        // Then
        assertThat(cargoCrates.getAllTop()).isEqualTo("VRWBSFZWM");
    }
}
