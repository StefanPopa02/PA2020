import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class RandomGenerator {
    private Faker faker;
    private List<Resident> residentList;
    private Set<Hospital> hospitalSet;
    private Map<Resident, List<Hospital>> resPrefMap;
    private Map<Hospital, List<Resident>> hosPrefMap;

    public List<Resident> getResidentList() {
        return residentList;
    }

    public Set<Hospital> getHospitalSet() {
        return hospitalSet;
    }

    public RandomGenerator() {
        faker = new Faker();
        residentList = new ArrayList<>();
        hospitalSet = new TreeSet<>();
    }

    public Resident[] getResidents() {
        var randomResidents = IntStream.rangeClosed(0, 10)
                .mapToObj(i -> new Resident(faker.name().firstName()))
                .toArray(Resident[]::new);

        residentList.addAll(Arrays.asList(randomResidents));
        Collections.sort(residentList,
                (Comparator.comparing(Resident::getName)));

        return randomResidents;
    }

    public Hospital[] getHospitals() {
        var randomHospitals = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Hospital(faker.name().firstName()))
                .toArray(Hospital[]::new);

        for (Hospital hospital : randomHospitals) {
            hospital.setCapacity(getRandomCapacity(1, 3));
        }

        hospitalSet.addAll(Arrays.asList(randomHospitals));
        return randomHospitals;
    }

    public Map<Resident, List<Hospital>> randomResidentPref() {
        resPrefMap = new HashMap<>();
        List<Hospital> chosenHospitals;

        for (Resident resident : residentList) {
            chosenHospitals = new ArrayList<>();
            for (Hospital hospital : hospitalSet) {
                if (getRandomCapacity(0, 1) == 1) {
                    chosenHospitals.add(hospital);
                }
            }
            resPrefMap.put(resident, chosenHospitals);
        }

        return resPrefMap;
    }

    public Map<Hospital, List<Resident>> randomHospitalPref() {
        hosPrefMap = new TreeMap<>();
        List<Resident> chosenResidents;

        for (Hospital hospital : hospitalSet) {
            chosenResidents = new ArrayList<>();
            for (Resident resident : residentList) {
                if (getRandomCapacity(0, 1) == 1) {
                    chosenResidents.add(resident);
                }
            }
            hosPrefMap.put(hospital, chosenResidents);
        }

        return hosPrefMap;
    }

    private int getRandomCapacity(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }


}
