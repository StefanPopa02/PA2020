import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var r = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);

        var h = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i))
                .toArray(Hospital[]::new);
        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);
        List<Resident> residentList = new ArrayList<>();
        residentList.addAll(Arrays.asList(r));
        Collections.sort(residentList,
                (Comparator.comparing(Resident::getName)));

        Set<Hospital> hospitalSet = new TreeSet<>();
        hospitalSet.addAll(Arrays.asList(h));

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        Map<Hospital, List<Resident>> hosPrefMap = new TreeMap<>();
        hosPrefMap.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[2], r[1]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));

        System.out.println("Residents pref:");
        Iterator it = resPrefMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());

        }

        System.out.println("Hospitals pref:");
        Iterator iterator2 = hosPrefMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator2.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
        System.out.println("Residents who find acceptable H0 and H2:");
        List<Hospital> target = Arrays.asList(h[0], h[2]);
        residentList.stream()
                .filter(res -> resPrefMap.get(res).containsAll(target))
                .forEach(System.out::println);

        System.out.println("Hospitals that have R0 as their top reference:");
        hospitalSet.stream()
                .filter(hos -> hosPrefMap.get(hos).get(0).compareTo(r[0]) == 0)
                .forEach(hos -> System.out.println(hos.getName()));

        System.out.println("=========================");
        System.out.println("Optional");
        System.out.println("=========================");

        Problem problem = new Problem(residentList, hospitalSet, resPrefMap, hosPrefMap);

        Matching matching = new Matching(problem);
        Map<Resident, Hospital> solution = matching.generateMatching();
        for (Resident res : solution.keySet()) {
            System.out.println(res + ":" + solution.get(res));
        }

        RandomGenerator randomGenerator = new RandomGenerator();
        Resident[] randomResidents = randomGenerator.getResidents();
        Hospital[] randomHospitals = randomGenerator.getHospitals();
        System.out.println("=========================");
        System.out.println("Residents:");
        System.out.println("=========================");
        for (Resident resident : randomResidents) {
            System.out.println(resident);
        }

        System.out.println("=========================");
        System.out.println("Hospitals:");
        System.out.println("=========================");
        for (Hospital hospital : randomHospitals) {
            System.out.println(hospital + " capacitate:" + hospital.getCapacity());
        }

        System.out.println("=========================");
        System.out.println("Residents prefference");
        System.out.println("=========================");
        Map<Resident,List<Hospital>>randomResidentPref=randomGenerator.randomResidentPref();
        for(Resident resident:randomResidentPref.keySet()){
            System.out.println("Resident: "+resident+" -> "+"Hospital: "+randomResidentPref.get(resident));
        }

        System.out.println("=========================");
        System.out.println("Hospitals prefference");
        System.out.println("=========================");
        Map<Hospital, List<Resident>> randomHospitalPref=randomGenerator.randomHospitalPref();
        for(Hospital hospital:randomHospitalPref.keySet()){
            System.out.println("Hospital: "+hospital+" -> "+"Resident: "+randomHospitalPref.get(hospital));
        }

        System.out.println("=========================");
        System.out.println("Matching");
        System.out.println("=========================");
        Problem randomInstance = new Problem(randomGenerator.getResidentList(),randomGenerator.getHospitalSet(),randomResidentPref,randomHospitalPref);
        Matching greedyMatching = new Matching(randomInstance);
        Map<Resident, Hospital> randomInstanceSolution = greedyMatching.generateMatching();
        for (Resident res : randomInstanceSolution.keySet()) {
            System.out.println(res + ":" + randomInstanceSolution.get(res));
        }

        System.out.println("=========================");
        System.out.println("Stable Matching");
        System.out.println("=========================");
        StableMatching stableMatching = new StableMatching(greedyMatching);
        //stableMatching.printChosenData();
        System.out.println(stableMatching.isStable());
    }
}
