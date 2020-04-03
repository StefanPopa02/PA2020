import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Token> board;
    private Integer turnNumber = 1;
    private int numOfPlayers;
    private int maximumValue;
    private int maximumPoints;
    private int minAP;
    private volatile boolean minApFound = false;

    public int getMaximumPoints() {
        return maximumPoints;
    }

    public int getMinAP() {
        return minAP;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public Board(List<Token> board, int numOfPlayers, int maximumValue, int minAP) {
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.maximumValue = maximumValue;
        this.maximumPoints=board.size();
        this.minAP = minAP;
    }

    public List<Token> getBoard() {
        return board;
    }

    public synchronized Token getToken(Player player) {
        while (this.turnNumber != player.getTurnNumber()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.print("Nr de jucatori "+numOfPlayers+player.getName()+" tura:"+this.turnNumber);
        this.turnNumber++;
        if (this.turnNumber > numOfPlayers) {
            this.turnNumber = 1;
        }
        if (!minApFound) {
            if (player.checkAP() >= minAP) {
                System.out.println("s-a gasit minAP");
                minApFound = true;
                notifyAll();
                return new Token(maximumValue + 2);//Jocul s-a terminat deoarece s-a atins progresia aritmetica de lungime k
            }
            //System.out.println(" si a trecut la tura"+this.turnNumber);
            if (!board.isEmpty() && !minApFound) {
                Token returnedToken = board.get(0);
                board.remove(0);
                notifyAll();
                return returnedToken;
            }
            notifyAll();
            return new Token(maximumValue + 1);//Jocul s-a terminat deoarece s-au terminat token-urile
        }else{
            notifyAll();
            return new Token(maximumValue+2);
        }
    }

}
