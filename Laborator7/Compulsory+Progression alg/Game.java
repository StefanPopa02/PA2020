import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Thread> threads;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        threads = new ArrayList<>();
    }

    public void begin() {
//        for (Player player : players) {
//            new Thread(player).start();
//        }

        for (int i = 0; i < players.size(); i++) {
            threads.add(new Thread(players.get(i)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Player player : players) {
            player.printTokens();
            player.setCurrentAp(player.checkAP());
            System.out.println("AP pentru " + player.getName() + " este " + player.getCurrentAp());
        }
        if (players.get(0).getTerminareJoc() == 1) {
            System.out.println("Jocul s-a incheiat -> Token-uri epuizate");
            generarePunctaje(1);

        } else if (players.get(0).getTerminareJoc() == 2) {
            System.out.println("Jocul s-a incheiat -> S-a facut o progresie aritmetica completa de dimensiune " + board.getMinAP());
            generarePunctaje(2);
        }


    }

    public void generarePunctaje(int terminareJoc) {
        if (terminareJoc == 2) {
            Player playerCastigator = players.get(0);
            for (Player player : players) {
                if (player.getCurrentAp() > playerCastigator.getCurrentAp()) {
                    playerCastigator = player;
                }
            }
            System.out.println("Punctaje:");
            System.out.println(playerCastigator.getName() + " : " + board.getMaximumPoints());
            for (Player player : players) {
                if (!player.equals(playerCastigator)) {
                    System.out.println(player.getName() + " : 0");
                }
            }
        } else if (terminareJoc == 1) {
            System.out.println("Punctaje:");
            for(Player player : players){
                System.out.println(player.getName()+" : "+player.getCurrentAp());
            }
        }

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
