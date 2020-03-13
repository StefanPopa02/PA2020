public class Book implements Item {
    private String name;
    private int pageNumber; //→ getWeight
    private double value;

    public Book(String name, int pageNumber, double value) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return pageNumber / 100;
    }

    @Override
    public String toString() {
        return "book: " + this.getName() + ", weight = " + this.getWeight() + ", value = " + this.getValue() + " (profit factor = " + this.profitFactor() + ")";
    }
}