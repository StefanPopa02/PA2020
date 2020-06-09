package sample.entity;

import java.util.List;

public class Ranking {
    private List<String> punctaje;

    public List<String> getPunctaje() {
        return punctaje;
    }

    public void setPunctaje(List<String> punctaje) {
        this.punctaje = punctaje;
    }

    @Override
    public String toString() {
        String clasament = "";
        for (String punctaj : punctaje) {
            clasament = clasament + punctaj + "\n";
        }
        return clasament;
    }
}
