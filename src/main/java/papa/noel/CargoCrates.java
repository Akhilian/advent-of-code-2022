package papa.noel;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

enum Mover {
    CrateMover_9000,
    CrateMover_9001,
}

public class CargoCrates {

    private final List<String> list;
    private Mover mover;
    private final ArrayList<LinkedList<String>> stacks;

    public CargoCrates(List<String> list, Mover mover) {
        this.list = list;
        this.mover = mover;

        ArrayList<LinkedList<String>> linkedLists = new ArrayList<>();

        for (int i = 0; i < this.getStacksCount(); i++) {
            linkedLists.add(new LinkedList<>());
        }

        List<String[]> collect = list.stream().filter(s -> s.contains("[")).map(s -> s.split("(?<=\\G.{4})")).toList();
        for (String[] stacks : collect) {
            List<String> cleanedStacks = Arrays.stream(stacks).map(String::trim).map(s -> s.replaceAll("[\\[\\]]", "")).collect(Collectors.toList());

            for (int i = 0; i < cleanedStacks.size(); i++) {
                String item = cleanedStacks.get(i);
                if(!item.equals("")) {
                    linkedLists.get(i).add(item);
                }
            }
        }

        this.stacks = linkedLists;
    }

    public String getTopOf(int i) {
        return this.stacks.get(i - 1).getFirst().toString();
    }

    public Integer getStacksCount() {
        Pattern stacksLabel = Pattern.compile("[a-zA-Z]+");

        String stacksDescription = list.stream().filter(stacksLabel.asPredicate().negate()).findFirst().get();
        return Arrays.stream(stacksDescription.replaceAll(" ", "").split("")).map(Integer::parseInt).max(Comparator.comparing(Integer::valueOf)).get();
    }

    public void moveCrates() {
        List<String[]> moves = this.list.stream()
                .filter(s -> s.startsWith("move"))
                .map(s -> s.replaceAll("[a-z]", "").trim())
                .map(s -> s.split("\\  ")).collect(Collectors.toList());

        for (String[] params : moves) {
            Integer quantity = Integer.valueOf(params[0]);
            Integer source = Integer.valueOf(params[1]);
            Integer destination = Integer.valueOf(params[2]);

            this.moveCrates(quantity, source, destination);
        }
    }

    private void moveCrates(Integer quantity, Integer source, Integer destination) {
        LinkedList<String> sourceStack = this.stacks.get(source - 1);
        LinkedList<String> destinationStack = this.stacks.get(destination - 1);

        if(mover == Mover.CrateMover_9000) {
            for (int i = 0; i < quantity; i++) {
                String crate = sourceStack.removeFirst().toString();
                destinationStack.addFirst(crate);
            }
        } else {
            LinkedList<String> list = new LinkedList<>();

            for (int i = 0; i < quantity; i++) {
                if (sourceStack.size() > 0) {
                    String crate = sourceStack.removeFirst();
                    list.addFirst(crate);
                }
            }

            for (String s : list) {
                destinationStack.addFirst(s);
            }
        }

    }

    public String getAllTop() {
        String allTop = "";

        for (Integer i = 0; i < this.getStacksCount(); i++) {
            allTop += this.stacks.get(i).getFirst().toString();
        }

        return allTop;
    }
}
