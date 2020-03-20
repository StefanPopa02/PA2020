import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StableMatching {
    private Map<Resident, Hospital> matching;
    private List<Resident> chosenResidents;
    private List<Hospital> chosenHospitals;
    private Map<Resident, List<Hospital>> residentPref;
    private Map<Hospital, List<Resident>> hospitalPref;

    public StableMatching(Matching matching) {
        this.matching = matching.getMatching();
        residentPref = matching.getProblem().getResPrefMap();
        hospitalPref = matching.getProblem().getHosPrefMap();
        chosenResidents = new ArrayList<>();
        chosenHospitals = new ArrayList<>();
        for (Resident resident : this.matching.keySet()) {
            chosenResidents.add(resident);
            chosenHospitals.add(this.matching.get(resident));
        }
    }

    public void printChosenData() {
        System.out.println("Chosen residents:");
        for (Resident resident : chosenResidents) {
            System.out.print(resident + " ");
        }
        System.out.println();
        System.out.println("Chosen hospitals:");
        for (Hospital hospital : chosenHospitals) {
            System.out.print(hospital + " ");
        }
        System.out.println();
    }

    /***
     * Pentru fiecare matching tinem minte prioritatea rezidentului fata de spital si vice versa
     * Verificam daca exista o pereche (resident,hospital) care are prioritatea mai buna fata de cea a matchingului existent
     *
     * @return
     */
    public boolean isStable() {
        for (Resident chosenResident : matching.keySet()) {
            Hospital chosenHospital = matching.get(chosenResident);
            List<Hospital> tmpResidentPref = residentPref.get(chosenResident);//lista cu spitale preferate de residentul temporar
            List<Resident> tmpHospitalPref = hospitalPref.get(chosenHospital);//lista cu residenti preferate de spitalul temporar
            int residentPriority = getResidentPriority(chosenHospital, tmpResidentPref);
            int hospitalPriority = getHospitalPriority(chosenResident, tmpHospitalPref);
            //System.out.println("Residentul " + chosenResident + " Spitalul " + chosenHospital + " " + residentPriority + ":" + hospitalPriority);
            int tmpResidentPriority = 0;
            int tmpHospitalPriority = 0;
            //System.out.println("Algoritm");
            for (Hospital hospital : tmpResidentPref) { //parcurg lista spitalelor rezidentului actual
                if (chosenHospitals.contains(hospital)) {//din lista totala de spitale preferate de rezident se aleg doar cele asignate matchingului
                    for (Resident resident : hospitalPref.get(hospital)) {//parcurgem lista de rezidenti la fiecare spital valabil preferintelor rezidentului temporar
                        if (resident == chosenResident) {
                            tmpResidentPriority = getResidentPriority(hospital, residentPref.get(resident));
                            tmpHospitalPriority = getHospitalPriority(resident, hospitalPref.get(hospital));
                            //System.out.println("Resident: " + resident + " Hospital: " + hospital + " " + tmpResidentPriority + ":" + tmpHospitalPriority);
                            if (tmpResidentPriority < residentPriority && tmpHospitalPriority < hospitalPriority) {
                                return false;
                            }
                        }
                    }
                }
            }
            //System.out.println();

        }
        return true;
    }

    private int getResidentPriority(Hospital chosenHospital, List<Hospital> tmpResidentPref) {
        int residentPriority = 0;
        for (int i = 0; i < tmpResidentPref.size(); i++) {
            if (chosenHospital == tmpResidentPref.get(i)) {
                residentPriority = i;//prioritatea rezidentului fata de spital relativ la matching-ul facut
                break;
            }
        }
        return residentPriority;
    }

    private int getHospitalPriority(Resident chosenResident, List<Resident> tmpHospitalPref) {
        int hospitalPriority = 0;
        for (int i = 0; i < tmpHospitalPref.size(); i++) {
            if (chosenResident == tmpHospitalPref.get(i)) {
                hospitalPriority = i;//prioritatea spitalului fata de rezident relativ la matching-ul facut
                break;
            }
        }
        return hospitalPriority;
    }
}
