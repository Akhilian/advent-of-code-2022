package papa.noel;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Organizer {
    private final List<Rucksack> rucksacks;

    public Organizer(List<Rucksack> rucksacks) {
        this.rucksacks = rucksacks;
    }

    public int getPrioritySum() {
        int total = 0;

        for (Rucksack rucksack : this.rucksacks) {
            List<Item> sameItems = rucksack.getSameItems();
            Integer sumForRucksack = sameItems.stream().map(Item::getPriority).reduce(0, Integer::sum);

            total += sumForRucksack;
        }
        return total;
    }

    public int getPrioritySumByTeamOf3() {
        List<Rucksack> rucksacks = new ArrayList<>(List.copyOf(this.rucksacks));
        ArrayList<List<Rucksack>> groups = new ArrayList<>();

        while (rucksacks.size() >= 3) {
            List<Rucksack> group = List.of(rucksacks.get(0), rucksacks.get(1), rucksacks.get(2));
            rucksacks.remove(0);
            rucksacks.remove(0);
            rucksacks.remove(0);
            groups.add(group);
        }

        int total = 0;

        for (List<Rucksack> group : groups) {
            Organizer organizer = new Organizer(group);
            total += organizer.getCommonItem().getPriority();
        }

        return total;
    }

    public Item getCommonItem() {
        List<String> allItems = this.rucksacks.get(0).getAllItems()
                .stream().map(item -> item.input).toList();
        List<String> allItems1 = this.rucksacks.get(1).getAllItems().stream().map(item -> item.input).toList();
        List<String> allItems2 = this.rucksacks.get(2).getAllItems().stream().map(item -> item.input).toList();

        List<Item> commonItems = allItems.stream().filter(allItems1::contains).filter(allItems2::contains).distinct()
                .map(Item::new).toList();

        return commonItems.get(0);
    }
}

class Item {
    protected final String input;

    public Item(String input) {
        this.input = input;
    }

    public int getPriority() {
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz".toLowerCase();
        String upperCaseAlphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();

        return (lowerCaseAlphabet + upperCaseAlphabet).indexOf(input) + 1;
    }
}

class Compartment {
    protected final List<String> items;

    public Compartment(String items) {
        this.items = List.of(items.split(""));
    }

    public List<Item> getAllItem() {
        return this.items.stream().map(Item::new).collect(Collectors.toList());
    }
}

public class Rucksack {

    private final List<Compartment> compartments;

    public Rucksack(String items) {
        String firstCompartment = items.substring(0, items.length() / 2);
        String secondCompartment = items.substring(items.length() / 2);
        compartments = List.of(new Compartment(firstCompartment), new Compartment(secondCompartment));
    }

    public List<Compartment> getCompartments() {
        return compartments;
    }

    public List<Item> getSameItems() {
        return compartments.get(0).items.stream()
                .distinct()
                .filter(compartments.get(1).items::contains).map(Item::new).collect(Collectors.toList());
    }

    public List<Item> getAllItems() {
        List<Item> allItems = new java.util.ArrayList<>(List.of());

        for (Compartment compartment : this.compartments) {
            allItems.addAll(compartment.getAllItem());
        }

        return allItems;
    }
}
