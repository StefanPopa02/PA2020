import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private String name;
    private Board board;
    private int turnNumber;
    private List<Token> tokenList;

    public String getName() {
        return name;
    }

    public Player(String name, Board board, int turnNumber) {
        this.name = name;
        this.board = board;
        this.turnNumber = turnNumber;
        tokenList = new ArrayList<>(board.getBoard().size());
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public void run() {
        boolean gameFinished = false;
        while (!gameFinished) {
            Token tmpToken = board.getToken(this);
            if (tmpToken.getNumber() == board.getMaximumValue()+1) {
                break;
            }
            System.out.println(name + " got token " + tmpToken.getNumber());
            tokenList.add(tmpToken);
        }
        System.out.println(name + " got " + tokenList.size() + " tokens");
    }
}
