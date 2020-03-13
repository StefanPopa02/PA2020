import java.util.ArrayList;
import java.util.List;

public class RandomKnapsack {

    public Knapsack getRandomKnapsack() {
        return new Knapsack(getRandomNumber(5, 20));
    }

    public List<Item> getRandomItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Book("Dragon Rising", getRandomNumber(300, 400), getRandomNumber(2, 6)));
        items.add(new Book("A Blade in the Dark", getRandomNumber(300, 400), getRandomNumber(2, 6)));
        items.add(new Food("Cabbage", getRandomNumber(2, 6)));
        items.add(new Food("Rabbit", getRandomNumber(2, 6)));
        items.add(new Weapon(WeaponType.SWORD, getRandomNumber(2, 6), getRandomNumber(2, 6)));
        return items;
    }

    public void compareAlgorithms(List<Item> items, Knapsack rucsac) {
        System.out.println("Random generator:");
        System.out.println("Capacitate: " + rucsac.getCapacity());
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + " Weight: " + item.getWeight() + " Value: " + item.getValue());
        }
        GreedyAlgorithm rucsacGreedy = new GreedyAlgorithm(items, rucsac);
        DynamicProgrammingAlgorithm rucsacDP = new DynamicProgrammingAlgorithm(items, rucsac);
        Knapsack resultGreedy = rucsacGreedy.solution();
        Knapsack resultDP = rucsacDP.solution();
        System.out.println("DP profit: " + resultDP.getValue() / resultDP.getWeight());
        System.out.println("Greedy profit: " + resultGreedy.getValue() / resultGreedy.getWeight());
    }

    private int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }


}
