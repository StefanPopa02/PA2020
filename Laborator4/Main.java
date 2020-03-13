import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        var r = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);

        var h = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i))
                .toArray(Hospital[]::new);
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

        Iterator it = resPrefMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());

        }

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
                .filter(hos -> hosPrefMap.get(hos).get(0).compareTo(r[0])==0)
                .forEach(hos -> System.out.println(hos.getName()));

    }
}
