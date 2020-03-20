import java.util.Comparator;

public class Hospital implements Comparable<Hospital> {
    private String name;
    private int capacity;

    public Hospital(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) obj;
        return name.equals(other.name);
    }


    @Override
    public int compareTo(Hospital o) {
        return this.name.compareTo(o.name);
    }
}
