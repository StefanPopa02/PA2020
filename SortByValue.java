import java.util.Comparator;

public class SortByValue implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        if (item1.profitFactor() == item2.profitFactor()) return 0;
        if (item1.profitFactor() > item2.profitFactor()) return -1;
        else return 1;
    }
}
