import java.util.Comparator;

public class Resident implements Comparable<Resident> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resident(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public int compareTo(Resident o) {
        return this.name.compareTo(o.name);
    }
}
