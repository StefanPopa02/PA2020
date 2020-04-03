import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public void begin() {
        for (Player player : players) {
            new Thread(player).start();
        }
        /*new Thread(players.get(0)).start();
        new Thread(players.get(1)).start();
        new Thread(players.get(2)).start();
        new Thread(players.get(3)).start();*/
    }

    public void printData() {
        System.out.println("Tokens:");
        for (Token token : board.getBoard()) {
            System.out.print(token.getNumber() + " ");
        }

        System.out.println();
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }
}
