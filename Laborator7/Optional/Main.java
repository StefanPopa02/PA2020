import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int number = 30;
        int maximumValue = 40;
        int key = 3;
        int numOfPlayers = 4;
        List<Token> tokenList = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            tokenList.add(new Token(getRandomNumber(0, maximumValue)));
        }
        Board board = new Board(tokenList, numOfPlayers, maximumValue, key);
        /*var players = IntStream.rangeClosed(0, numOfPlayers - 1)
                .mapToObj(i -> new RandomPlayer("Player" + (i + 1), board, i + 1))
                .toArray(RandomPlayer[]::new);*/

        List<Player> playerList = new ArrayList<>();
        //playerList.addAll(Arrays.asList(players));
        Player player1= new RandomPlayer("Player1",board,1);
        Player player2= new RandomPlayer("Player2",board,2);
        Player player3= new SmartPlayer("SmartPlayer1",board,3);
        Player Stefan=new ManualPlayer("Stefan",board,4);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(Stefan);
        Game game = new Game(board, playerList);
        //game.printData();
        game.begin();

    }

    public static Integer getRandomNumber(int min, int max) {
        int range = max - min + 1;
        int number = (int) (Math.random() * range) + min;
        return number;
    }
}
