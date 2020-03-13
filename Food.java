public class Food implements Item {
    private String name;
    private double weight; // → getWeight, getValue

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return weight * 2;
    }

    @Override
    public String toString() {
        return "food: " + this.getName() + ", weight = " + this.getWeight() + ", value = " + this.getValue() + " (profit factor = " + this.profitFactor() + ")";
    }
}