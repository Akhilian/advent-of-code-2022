package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CaloryCounterTest {

    @Test
    void should_find_5_elves() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        assertEquals(caloryCounter.getNumberOfElves(), 5);
    }

    @Test
    void should_find_3_inventory_items_for_first_elf() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        assertEquals(caloryCounter.getInventorySizeFor(0), 3);
    }

    @Test
    void should_find_1_inventory_items_for_second_elf() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        assertEquals(caloryCounter.getInventorySizeFor(1), 1);
        assertEquals(caloryCounter.getInventorySizeFor(1), 1);
    }

    @Test
    void should_find_the_elf_with_the_maximum_calories() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        assertThat(caloryCounter.getElfWithMaximumCalory())
                .usingRecursiveComparison().isEqualTo(new Elf("""
                        7000
                        8000
                        9000
                        """));
    }

    @Test
    void should_find_the_elf_with_the_maximum_calory_with_another_input() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        Elf elf = caloryCounter.getElfWithMaximumCalory();
        assertThat(elf)
                .usingRecursiveComparison().isEqualTo(new Elf("""
                        5000
                        6000
                        """));
        assertEquals(elf.getCalories(), 11000);
    }

    @Test
    void should_find_the_thre_elf_with_the_maximum_calory_with_another_input() {
        // Given
        String input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;

        // When
        CaloryCounter caloryCounter = new CaloryCounter(input);

        // Then
        List<Elf> elves = caloryCounter.getThreeElvesWithMaximumCalories();
        assertThat(elves).usingRecursiveComparison().isEqualTo(List.of(
                        new Elf("""
                                10000
                                """),
                        new Elf("""
                                5000
                                6000
                                """),
                        new Elf("""
                                7000
                                8000
                                9000
                                """)
                )
        );
    }

    @Test
    void day_1_first_challenge() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day1"));
        String join = String.join("\n", strings);

        CaloryCounter caloryCounter = new CaloryCounter(join);
        Elf elfWithMaximumCalory = caloryCounter.getElfWithMaximumCalory();
        assertEquals(elfWithMaximumCalory.getCalories(), 71124);
    }

    @Test
    void day_1_second_challenge() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day1"));
        String join = String.join("\n", strings);

        CaloryCounter caloryCounter = new CaloryCounter(join);
        List<Elf> threeElvesWithMaximumCalories = caloryCounter.getThreeElvesWithMaximumCalories();

        Long reduce = threeElvesWithMaximumCalories.stream().map(Elf::getCalories).reduce(0L, Long::sum);

        assertEquals(reduce, 71124);
    }
}
