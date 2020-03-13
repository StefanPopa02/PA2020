import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgrammingAlgorithm implements Algorithm {

    private List<Item> items = new ArrayList<>();
    private Knapsack rucsac;

    public DynamicProgrammingAlgorithm(List<Item> items, Knapsack rucsac) {
        this.items = items;
        this.rucsac = rucsac;
    }

    public Knapsack solution() {
        System.out.println("DynamicProgramming:");
        double capacitate = rucsac.getCapacity();
        double nrIteme = items.size();
        double matrix[][] = new double[(int) nrIteme][(int) capacitate + 1];
        for (int i = 0; i < nrIteme; i++) {
            for (int j = 0; j < capacitate + 1; j++) {
                matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < nrIteme; i++) {
            for (int j = 0; j <= capacitate; j++) {
                if (i != 0) {
                    if (items.get(i - 1).getWeight() > j) {
                        matrix[i][j] = matrix[i - 1][j];
                    } else {
                        matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - (int) items.get(i - 1).getWeight()] + items.get(i - 1).getValue());
                    }
                }
            }
        }

        for (int i = 0; i < nrIteme; i++) {
            for (int j = 0; j < capacitate + 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        double valoare = matrix[(int) nrIteme - 1][(int) rucsac.getCapacity()];
        int index = (int) nrIteme;
        while (valoare > 0) {
            if (matrix[index - 1][(int) capacitate] == matrix[index - 2][(int) capacitate]) {
                index--;
            } else {
                rucsac.addItem(items.get(index - 2));
                valoare = valoare - items.get(index - 2).getValue();
                capacitate = capacitate - items.get(index - 2).getWeight();
                index--;
            }
        }

        System.out.println(rucsac);
        return rucsac;
    }
}
