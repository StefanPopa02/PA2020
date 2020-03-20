import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem {
    private List<Resident> residentList;
    private Set<Hospital> hospitalSet;
    private Map<Resident, List<Hospital>> resPrefMap;
    private Map<Hospital, List<Resident>> hosPrefMap;
    private Map<Hospital, Integer> availablePosts;

    public Problem(List<Resident> residentList, Set<Hospital> hospitalSet, Map<Resident, List<Hospital>> resPrefMap, Map<Hospital, List<Resident>> hosPrefMap) {
        this.residentList = residentList;
        this.hospitalSet = hospitalSet;
        this.resPrefMap = resPrefMap;
        this.hosPrefMap = hosPrefMap;
        this.availablePosts = new HashMap<>();
        for (Hospital hos : hospitalSet) {
            availablePosts.put(hos, hos.getCapacity());
        }
    }

    public List<Resident> getResidentList() {
        return residentList;
    }

    public void setResidentList(List<Resident> residentList) {
        this.residentList = residentList;
    }

    public Set<Hospital> getHospitalSet() {
        return hospitalSet;
    }

    public void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitalSet = hospitalSet;
    }

    public Map<Resident, List<Hospital>> getResPrefMap() {
        return resPrefMap;
    }

    public void setResPrefMap(Map<Resident, List<Hospital>> resPrefMap) {
        this.resPrefMap = resPrefMap;
    }

    public Map<Hospital, List<Resident>> getHosPrefMap() {
        return hosPrefMap;
    }

    public void setHosPrefMap(Map<Hospital, List<Resident>> hosPrefMap) {
        this.hosPrefMap = hosPrefMap;
    }

    public Map<Hospital, Integer> getAvailablePosts() {
        return availablePosts;
    }

    public void setAvailablePosts(Map<Hospital, Integer> availablePosts) {
        this.availablePosts = availablePosts;
    }
}
