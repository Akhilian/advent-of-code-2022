package papa.noel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Elf {
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
