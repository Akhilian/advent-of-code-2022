package papa.noel;

import java.util.List;

class Pair {
    private final Integer maxElf2;
    private final Integer minElf1;
    private final Integer maxElf1;
    private final Integer minElf2;

    public Pair(String elf1, String elf2) {
        String[] minMaxElf1 = elf1.split("-");
        minElf1 = Integer.valueOf(minMaxElf1[0]);
        maxElf1 = Integer.valueOf(minMaxElf1[1]);


        String[] minMaxElf2 = elf2.split("-");
        minElf2 = Integer.valueOf(minMaxElf2[0]);
        maxElf2 = Integer.valueOf(minMaxElf2[1]);
    }

    @Override
    public String toString() {
        return "%d-%d,%d-%d".formatted(minElf1, maxElf1, minElf2, maxElf2);
    }

    public Boolean isFullyContained() {
        return (minElf1 <= minElf2 && maxElf1 >= maxElf2)  || (minElf1 >= minElf2 && maxElf1 <= maxElf2);
    }

    private boolean isBetween(int a, int b, int c) {
        return a >= b && a <= c;
    }

    public boolean isOverlapping() {
        return isBetween(minElf1, minElf2, maxElf2) || isBetween(maxElf1, minElf2, maxElf2) || this.isFullyContained();
    }
}

public class CampCleanup {

    private final List<Pair> pairs;

    public CampCleanup(List<String> input) {
        pairs = input.stream().map(i -> i.split(",")).map(p -> new Pair(p[0], p[1])).toList();
    }

    public long getFullOverlapCleanupAssignment() {
        return this.pairs.stream().map(Pair::isFullyContained).filter(i -> i).count();
    }

    public long getOverlapCleanupAssignment() {
        return this.pairs.stream().map(Pair::isOverlapping).filter(i -> i).count();
    }
}
