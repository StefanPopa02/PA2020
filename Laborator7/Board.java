import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Token> board;
    private Integer turnNumber = 1;
    private int numOfPlayers;
    private int maximumValue;

    public int getMaximumValue() {
        return maximumValue;
    }

    public Board(List<Token> board, int numOfPlayers, int maximumValue) {
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.maximumValue=maximumValue;
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
        //System.out.println(" si a trecut la tura"+this.turnNumber);
        if (!board.isEmpty()) {
            Token returnedToken = board.get(0);
            board.remove(0);
            notifyAll();
            return returnedToken;
        }
        notifyAll();
        return new Token(maximumValue+1);
    }
}
