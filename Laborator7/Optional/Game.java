import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Thread> threads;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        board.setPlayers(players);
        threads = new ArrayList<>();
    }

    public void begin() {

        Timekeeper timekeeper = new Timekeeper(board);
        board.setTimekeeper(timekeeper);
        for (int i = 0; i < players.size(); i++) {
            threads.add(new Thread(players.get(i)));
        }
        threads.add(new Thread(timekeeper));
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();//jocul s-a terminat, astept finalizarea tuturor thread-urilor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Player player : players) {
            player.printTokens();
            player.setCurrentAp(player.checkAP());
            System.out.print("Progresia aritmetica pentru " + player.getName()+" [");
            for(Integer integer : player.getMaxRatioNumbers()){
                System.out.print(integer+", ");
            }
            System.out.print("]");
            System.out.println();
        }
        if (players.get(0).getTerminareJoc() == 1) {
            System.out.println("Jocul s-a incheiat -> Token-uri epuizate");
            generarePunctaje(1);

        } else if (players.get(0).getTerminareJoc() == 2) {
            System.out.println("Jocul s-a incheiat -> S-a facut o progresie aritmetica completa de dimensiune " + board.getMinAP());
            generarePunctaje(2);
        } else if (players.get(0).getTerminareJoc() == 3) {
            System.out.println("Timpul a expirat");
            generarePunctaje(1);
        }


    }

    public void generarePunctaje(int terminareJoc) {
        if (terminareJoc == 2) { //Toate punctele la castigator
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
        } else if (terminareJoc == 1) { //Impartire punctaje in functie de progresia dobandita
            System.out.println("Punctaje:");
            for (Player player : players) {
                System.out.println(player.getName() + " : " + player.getCurrentAp());
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
