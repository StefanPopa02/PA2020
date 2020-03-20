import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matching {
    private Problem problem;
    private Map<Resident, Hospital> matching;
    private List<Hospital> hospitalsPrefered;
    private Map<Hospital, Integer> availablePosts;

    public Map<Resident, Hospital> getMatching() {
        return matching;
    }

    public Problem getProblem() {
        return problem;
    }

    public Matching(Problem problem) {
        this.problem = problem;
        matching = new HashMap<>();
        availablePosts = problem.getAvailablePosts();
    }

    public Map<Resident, Hospital> generateMatching() {

        for (Resident resident : problem.getResPrefMap().keySet()) {
            hospitalsPrefered = problem.getResPrefMap().get(resident);
            for (Hospital hospital : hospitalsPrefered) {
                if (availablePosts.get(hospital) != 0 && problem.getHosPrefMap().get(hospital).contains(resident)) {
                    matching.put(resident, hospital);
                    Integer integer = availablePosts.get(hospital);
                    integer--;
                    availablePosts.put(hospital, integer);
                    break;
                }
            }
        }

        return matching;
    }
}


