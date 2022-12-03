package papa.noel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Elf {
    private final String[] inventory;

    public Elf(String inventoryInput) {
        inventory = inventoryInput.split("\n");
    }

    public long getInventorySize() {
        return Arrays.stream(inventory).count();
    }

    public long getCalories() {
        return Arrays.stream(inventory).mapToInt(Integer::parseInt).sum();
    }
}

public class CaloryCounter {
    private final List<Elf> elves;

    public CaloryCounter(String input) {
        String[] elvesCalory = input.split("\n\n");
        elves = Arrays.stream(elvesCalory).map(Elf::new).toList();
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
