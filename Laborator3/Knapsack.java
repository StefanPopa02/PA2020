import java.util.*;

public class Knapsack {
    private double capacity;
    private List<Item> items = new ArrayList<>();

    public Knapsack(double capacity, List<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getValue() {
        double value = 0;
        for (Item item : items) {
            value += item.getValue();
        }
        return value;
    }

    public double getWeight() {
        double weight = 0;
        for (Item item : items) {
            weight += item.getWeight();
        }
        return weight;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        double value = 0;
        double weight = 0;
        Collections.sort(items, Comparator.comparing(Item::getName));
        String result = "selected items: \n";
        for (Item item : items) {
            result += item.toString() + "\n";
            value += item.getValue();
            weight += item.getWeight();
        }
        result += "(total weight=" + weight + ", total value=" + value + ")";
        return result;
    }
}