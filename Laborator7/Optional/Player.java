import java.util.*;

public abstract class Player implements Runnable {
    protected String name;
    protected Board board;
    protected int turnNumber;
    protected Set<Token> tokenSet;
    protected List<Integer> maxRatioNumbers;
    protected int maximumValue;
    protected int terminareJoc;
    protected int currentAp;

    public Set<Token> getTokenSet() {
        return tokenSet;
    }

    public List<Integer> getMaxRatioNumbers() {
        return maxRatioNumbers;
    }

    public int getCurrentAp() {
        return currentAp;
    }

    public void setCurrentAp(int currentAp) {
        this.currentAp = currentAp;
    }

    public int getTerminareJoc() {
        return terminareJoc;
    }

    public String getName() {
        return name;
    }


    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public void run() {
        boolean gameFinished = false;
        while (!gameFinished) {
            Token tmpToken = board.getToken(this);
            //System.out.println(name + " a primit " + tmpToken.getNumber());
            if (tmpToken.getNumber() > board.getMaximumValue()) {
                if (tmpToken.getNumber() == board.getMaximumValue() + 1) {
                    terminareJoc = 1;//s-au terminat token-urile
                } else if (tmpToken.getNumber() == board.getMaximumValue() + 2) {
                    terminareJoc = 2;//s-a gasit progresia aritmetica completa
                } else if (tmpToken.getNumber() == board.getMaximumValue() + 3) {
                    terminareJoc = 3;//a expirat timpul
                }
                break;
            }
            tokenSet.add(tmpToken);
        }

    }

    public void printTokens() {
        System.out.print(name + " [ ");
        for (Token token : tokenSet) {
            System.out.print(token.getNumber() + ", ");
        }
        System.out.println("]");
    }

    /***
     * Cel mai bine se poate explica pe un exemplu:
     * Fie TreeSet-ul=[1,2,3,6,7,8,10,11,12,14]
     * pentru fiecare valoare vedem daca se poate forma o progresie aritmetica cu valorile urmatoare valorii curente din j in j
     * 1 <= j < maximumValue
     * de exemplu pentru prima valoare =1 :
     * se verifica daca treeset-ul contine valoarea aleasa +j (in prima iteratie j=1), deci se verifica daca exista 2
     * Daca exista valoarea cautata(in cazul nostru, da) se contorizeaza si se trece la urmatoarea valoare, adica 3 in cazul nostru,
     * iar se contorizeaza si tot asa pana cand nu exista valoarea in treeSet, dupa care se incrementeaza j-ul si se merge din
     * 2 in 2 respectand aceeasi logica
     * Pentru j=1 va gasi 1,2,3 de lungime 3
     * Pentru j=2 va gasi 6,8,10,12,14 de lungime 5
     * Rezultat pentru exemplul ales = 4
     * @return lungimea progresiei aritmetice maxime
     */
    public int checkAP() {
        int maxApFound = 0;
        int actualValue = 0;
        Iterator<Token> chosenValue = tokenSet.iterator();
        while (chosenValue.hasNext()) {
            Token chosenToken = chosenValue.next();//valoare x aleasa din TreeSet
            for (int j = 1; j < maximumValue; j++) {// incercam sa gasim subseturi care formeaza progresie din j in j
                actualValue = 1;
                List<Integer> tmpMaxRatioNumbers=new ArrayList<>();
                tmpMaxRatioNumbers.add(chosenToken.getNumber());
                for (int k = j; k < maximumValue; k = k + j) {
                    if (tokenSet.contains(new Token(chosenToken.getNumber() + k))) {
                        actualValue++;
                        tmpMaxRatioNumbers.add(chosenToken.getNumber()+k);
                        if (actualValue > maxApFound) {
                            maxApFound = actualValue;
                            maxRatioNumbers=new ArrayList<>(tmpMaxRatioNumbers);
                        }
                    } else {
                        tmpMaxRatioNumbers.clear();
                        tmpMaxRatioNumbers.add(chosenToken.getNumber());
                        break;// daca se opreste lantul de valori in progresie se trece la urmatoarea valoare
                    }
                }
            }
        }

        return maxApFound;
    }

}
