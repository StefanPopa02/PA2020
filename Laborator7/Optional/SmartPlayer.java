import java.util.TreeSet;

public class SmartPlayer extends Player {
    public SmartPlayer(String name, Board board, int turnNumber) {
        this.name = name;
        this.board = board;
        this.turnNumber = turnNumber;
        this.maximumValue = board.getMaximumValue();
        tokenSet = new TreeSet<>();
    }
}
