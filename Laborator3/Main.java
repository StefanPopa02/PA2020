import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Book("Dragon Rising", 300, 5));
        items.add(new Book("A Blade in the Dark", 300, 5));
        items.add(new Food("Cabbage", 2));
        items.add(new Food("Rabbit", 2));
        items.add(new Weapon(WeaponType.SWORD, 5, 10));
        Knapsack rucsac = new Knapsack(10, items);
        System.out.print("Capacitatea este:");
        System.out.println(rucsac.getCapacity());
        for (Item item : items) {
            System.out.println(item.toString());
        }
        //Optional
        Knapsack rucsacGreedy = new Knapsack(10);
        GreedyAlgorithm greedySolution = new GreedyAlgorithm(items, rucsacGreedy);
        Knapsack rezultatGreedy = greedySolution.solution();
        System.out.println("Weight: " + rezultatGreedy.getWeight() + " Value: " + rezultatGreedy.getValue());
        Knapsack rucsacDP = new Knapsack(10);
        DynamicProgrammingAlgorithm dpSolution = new DynamicProgrammingAlgorithm(items, rucsacDP);
        dpSolution.solution();

        //Random Generator
        RandomKnapsack RandomKnapsack = new RandomKnapsack();
        List<Item> randomItems = RandomKnapsack.getRandomItems();
        Knapsack randomKnapsack = RandomKnapsack.getRandomKnapsack();
        RandomKnapsack.compareAlgorithms(randomItems, randomKnapsack);
    }
}
