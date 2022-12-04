package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CampCleanupTest {
    @Test
    void Pair_is_fully_contained_exactly() {
        assertThat(new Pair("1-5", "1-5").isFullyContained()).isTrue();
    }

    @Test
    void Pair_is_not_contained() {
        assertThat(new Pair("1-2", "3-4").isFullyContained()).isFalse();
        assertThat(new Pair("2-3", "4-5").isFullyContained()).isFalse();
    }

    @Test
    void Pair_is_fully_contained() {
        assertThat(new Pair("1-5", "3-4").isFullyContained()).isTrue();
    }

    @Test
    void Pair_is_fully_contained_with_another_example() {
        assertThat(new Pair("2-8", "3-7").isFullyContained()).isTrue();
        assertThat(new Pair("3-7", "2-8").isFullyContained()).isTrue();
    }

    @Test
    void Pair_is_fully_contained_with_sides_overlap() {
        assertThat(new Pair("6-6", "4-6").isFullyContained()).isTrue();
        assertThat(new Pair("4-6", "6-6").isFullyContained()).isTrue();
    }

    @Test
    void Pair_is_fully_contained_when_switching_pairs() {
        assertThat(new Pair("3-4", "1-5").isFullyContained()).isTrue();
    }

    @Test
    void Pair_is_partially_overlapping() {
        assertThat(new Pair("3-6", "6-8").isOverlapping()).isTrue();
    }

    @Test
    void Pair_is_partially_overlapping_on_the_over_side() {
        assertThat(new Pair("6-8", "3-6").isOverlapping()).isTrue();
    }

    @Test
    void Pair_is_not_overlapping() {
        assertThat(new Pair("2-4", "6-8").isOverlapping()).isFalse();
    }

    @Test
    void Pair_is_not_overlapping_second_example() {
        assertThat(new Pair("2-3", "4-5").isOverlapping()).isFalse();
    }

    @Test
    void Pair_is_overlapping_on_one_side() {
        assertThat(new Pair("5-7", "7-9").isOverlapping()).isTrue();
    }

    @Test
    void Pair_is_fully_overlapping() {
        assertThat(new Pair("2-8", "3-7").isOverlapping()).isTrue();
    }

    @Test
    void count_pairs_that_fully_overlap() {
        // Given
        List<String> input = List.of("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                                
                """.split("\n"));

        // When
        CampCleanup campCleanup = new CampCleanup(input);

        // Then
        assertThat(campCleanup.getFullOverlapCleanupAssignment()).isEqualTo(2);
    }

    @Test
    void count_pairs_with_more_complex_example() {
        // Given
        List<String> input = List.of("""
                23-33,24-65
                10-24,23-88
                71-92,18-71
                                
                """.split("\n"));

        // When
        CampCleanup campCleanup = new CampCleanup(input);

        // Then
        assertThat(campCleanup.getFullOverlapCleanupAssignment()).isEqualTo(0);
    }

    @Test
    void count_pairs_that_partially_overlap_with_more_complex_example() {
        // Given
        List<String> input = List.of("""
                23-33,24-65
                10-24,23-88
                71-92,18-71
                2-2,10-95
                24-26,25-66
                36-99,19-93
                                
                """.split("\n"));

        // When
        CampCleanup campCleanup = new CampCleanup(input);

        // Then
        assertThat(campCleanup.getOverlapCleanupAssignment()).isEqualTo(5);
    }

    @Test
    void day_4_first_challenge() throws IOException {
        // Given
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/input_day4"));

        // When
        CampCleanup campCleanup = new CampCleanup(input);

        // Then
        assertThat(campCleanup.getFullOverlapCleanupAssignment()).isEqualTo(595L);
    }

    @Test
    void day_4_second_challenge() throws IOException {
        // Given
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/input_day4"));

        // When
        CampCleanup campCleanup = new CampCleanup(input);

        // Then
        assertThat(campCleanup.getOverlapCleanupAssignment()).isEqualTo(952L);
    }
}
