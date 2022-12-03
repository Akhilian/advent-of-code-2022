package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class RucksackTest {
    @Test
    void day_3_rucksacks_with_simple_input() {
        // When
        Rucksack rucksack = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");

        // Then
        assertThat(rucksack.getCompartments())
                .usingRecursiveComparison()
                .isEqualTo(List.of(new Compartment("vJrwpWtwJgWr"), new Compartment("hcsFMMfFFhFp")));
    }

    @Test
    void day_3_rucksacks_with_second_example() {
        // When
        Rucksack rucksack = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");

        // Then
        assertThat(rucksack.getCompartments())
                .usingRecursiveComparison()
                .isEqualTo(List.of(new Compartment("jqHRNqRjqzjGDLGL"), new Compartment("rsFMfFZSrLrFZsSL")));
        assertThat(rucksack.getSameItems()).usingRecursiveComparison().isEqualTo(List.of(new Item("L")));
    }

    @Test
    void day_3_rucksacks_with_same_items_in_the_two_compartments() {
        // When
        Rucksack rucksack = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");

        // Then
        assertThat(rucksack.getSameItems()).usingRecursiveComparison().isEqualTo(List.of(new Item("p")));
    }

    @Test
    void day_3_rucksacks_with_other_example() {
        // Then
        assertThat(new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn").getSameItems())
                .usingRecursiveComparison().isEqualTo(List.of(new Item("v")));
        assertThat(new Rucksack("ttgJtRGJQctTZtZT").getSameItems())
                .usingRecursiveComparison().isEqualTo(List.of(new Item("t")));
        assertThat(new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw").getSameItems())
                .usingRecursiveComparison().isEqualTo(List.of(new Item("s")));
        assertThat(new Rucksack("wsLwLmpwMDwCrZsJsPPZsGzw").getSameItems())
                .usingRecursiveComparison().isEqualTo(List.of(new Item("w"), new Item("s")));
    }

    @Test
    void day_3_find_priority() {
        // Given
        List<Item> items = new Rucksack("aa").getSameItems();

        // Then
        assertThat(items.get(0).getPriority()).isEqualTo(1);
        assertThat(new Rucksack("bb").getSameItems().get(0).getPriority()).isEqualTo(2);
        assertThat(new Rucksack("zz").getSameItems().get(0).getPriority()).isEqualTo(26);
        assertThat(new Rucksack("AA").getSameItems().get(0).getPriority()).isEqualTo(27);
        assertThat(new Rucksack("pp").getSameItems().get(0).getPriority()).isEqualTo(16);
        assertThat(new Rucksack("LL").getSameItems().get(0).getPriority()).isEqualTo(38);
        assertThat(new Rucksack("PP").getSameItems().get(0).getPriority()).isEqualTo(42);
        assertThat(new Rucksack("vv").getSameItems().get(0).getPriority()).isEqualTo(22);
        assertThat(new Rucksack("tt").getSameItems().get(0).getPriority()).isEqualTo(20);
        assertThat(new Rucksack("ss").getSameItems().get(0).getPriority()).isEqualTo(19);
    }

    @Test
    void day_3_with_full_example() {
        // Given
        List<Rucksack> rucksacks = List.of(
                new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp"),
                new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
                new Rucksack("PmmdzqPrVvPwwTWBwg"),
                new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
                new Rucksack("ttgJtRGJQctTZtZT"),
                new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw")
        );

        // When
        Organizer organizer = new Organizer(rucksacks);

        // Then
        assertThat(organizer.getPrioritySum()).isEqualTo(157);
    }

    @Test
    void day_3_can_get_all_items_from_rushshake() {
        // Given
        Rucksack bag = new Rucksack("vJrw");

        // When
        List<Item> allItems = bag.getAllItems();

        // Then
        assertThat(allItems).usingRecursiveComparison()
                .isEqualTo(List.of(new Item("v"), new Item("J"), new Item("r"), new Item("w")));
    }

    @Test
    void day_3_with_group_of_three() {
        // Given
        List<Rucksack> rucksacks = List.of(
                new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp"),
                new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
                new Rucksack("PmmdzqPrVvPwwTWBwg")
        );

        // When
        Organizer organizer = new Organizer(rucksacks);

        // Then
        assertThat(organizer.getCommonItem()).usingRecursiveComparison().isEqualTo(new Item("r"));
    }

    @Test
    void day_3_with_another_group_of_three_elves() {
        // Given
        List<Rucksack> rucksacks = List.of(
                new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
                new Rucksack("ttgJtRGJQctTZtZT"),
                new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw")
        );

        // When
        Organizer organizer = new Organizer(rucksacks);

        // Then
        assertThat(organizer.getCommonItem()).usingRecursiveComparison().isEqualTo(new Item("Z"));
    }

    @Test
    void day_3_sum_of_priorities_by_group_of_3() {
        // Given
        List<Rucksack> rucksacks = List.of(
                new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp"),
                new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
                new Rucksack("PmmdzqPrVvPwwTWBwg"),
                new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
                new Rucksack("ttgJtRGJQctTZtZT"),
                new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw")
        );

        // When
        Organizer organizer = new Organizer(rucksacks);

        // Then
        assertThat(organizer.getPrioritySumByTeamOf3()).isEqualTo(70);
    }

    @Test
    void day_3_first_challenge_with_input() throws IOException {
        // Given
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day3"));
        List<Rucksack> rucksackStream = strings.stream().map(Rucksack::new).collect(Collectors.toList());


        // When
        Organizer organizer = new Organizer(rucksackStream);

        // Then
        assertThat(organizer.getPrioritySum()).isEqualTo(7737);
    }

    @Test
    void day_3_second_challenge_with_input() throws IOException {
        // Given
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day3"));
        List<Rucksack> rucksackStream = strings.stream().map(Rucksack::new).collect(Collectors.toList());


        // When
        Organizer organizer = new Organizer(rucksackStream);

        // Then
        assertThat(organizer.getPrioritySumByTeamOf3()).isEqualTo(2697);
    }
}
