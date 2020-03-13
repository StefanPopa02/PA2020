import java.util.*;

public class GreedyAlgorithm implements Algorithm {
    List<Item> items = new ArrayList<>();
    Knapsack rucsac;

    public GreedyAlgorithm(List<Item> items, Knapsack rucsac) {
        this.items = items;
        this.rucsac = rucsac;
    }

    public Knapsack solution() {
        System.out.println("Greedy:");
        Collections.sort(items, new SortByValue());
        double capacitate = rucsac.getCapacity();
        for (int i = 0; i < items.size(); i++) {
            if (capacitate - items.get(i).getWeight() >= 0) {
                rucsac.addItem(items.get(i));
                capacitate = capacitate - items.get(i).getWeight();
            }
        }

        System.out.println(rucsac);
        return rucsac;
    }
}
