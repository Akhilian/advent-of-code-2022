package papa.noel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CaloryCounter {
    private final List<Elf> elves;

    public CaloryCounter(String input) {
        String[] elvesCalory = input.split("\n\n");
        elves = Arrays.stream(elvesCalory).map(inventory -> new Elf(inventory)).toList();
    }

    public long getNumberOfElves() {
        return elves.size();
    }

    public long getInventorySizeFor(int i) {
        return elves.get(i).getInventorySize();
    }

    public Elf getElfWithMaximumCalory() {
        return elves.stream().max(Comparator.comparing(Elf::getCalories)).orElse(null);

    }

    public List<Elf> getThreeElvesWithMaximumCalories() {
        List<Elf> collect = elves.stream().sorted(Comparator.comparing(Elf::getCalories)).toList();
        return collect.subList(collect.size()-3, collect.size());
    }
}
