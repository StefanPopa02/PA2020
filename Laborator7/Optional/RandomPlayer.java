import java.util.*;

public class RandomPlayer extends Player {
    public RandomPlayer(String name, Board board, int turnNumber) {
        this.name = name;
        this.board = board;
        this.turnNumber = turnNumber;
        this.maximumValue = board.getMaximumValue();
        tokenSet = new TreeSet<>();
    }
}
