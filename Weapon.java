public class Weapon implements Item {
    private WeaponType type;
    private double weight;
    private double value;

    public Weapon(WeaponType type, double weight, double value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public String toString() {
        return "weapon: " + this.getName() + ", weight = " + this.getWeight() + ", value = " + this.getValue() + " (profit factor = " + this.profitFactor() + ")";
    }
}

