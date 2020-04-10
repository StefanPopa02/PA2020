import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private List<Token> board;
    private List<Player> players;
    private Integer turnNumber = 1;
    private int numOfPlayers;
    private int maximumValue;
    private int maximumPoints;
    private int minAP;
    private volatile boolean minApFound = false;
    private volatile Timekeeper timekeeper;
    private volatile boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setTimekeeper(Timekeeper timekeeper) {
        this.timekeeper = timekeeper;
    }

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
        this.maximumPoints = board.size();
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

        this.turnNumber++;
        if (this.turnNumber > numOfPlayers) {
            this.turnNumber = 1;
        }
        if (!timekeeper.isTimeUp()) {
            if (!minApFound) {
                if (player.checkAP() >= minAP) {
                    System.out.println("s-a gasit minAP");
                    minApFound = true;
                    notifyAll();
                    gameOver = true;
                    return new Token(maximumValue + 2);//Jocul s-a terminat deoarece s-a atins progresia aritmetica de lungime k
                }

                if (!board.isEmpty() && !minApFound) {
                    if (player instanceof RandomPlayer) {
                        Token returnedToken = board.get(0);
                        System.out.println(player.getName() + " a luat token-ul " + returnedToken.getNumber());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        board.remove(0);
                        notifyAll();
                        return returnedToken;
                    } else if (player instanceof ManualPlayer) {
                        player.printTokens();
                        System.out.println("[" + player.getName() + "]" + " Choose the index for your number:");
                        for (int i = 0; i < board.size(); i++) {
                            System.out.print("(" + i + "," + board.get(i).getNumber() + ")" + " ");
                        }
                        System.out.println();
                        Scanner scanner = new Scanner(System.in);
                        int chosenIndexOfNumber = scanner.nextInt();
                        scanner.nextLine();
                        Token returnedToken = board.get(chosenIndexOfNumber);
                        System.out.println("You chose " + returnedToken.getNumber());
                        board.remove(chosenIndexOfNumber);
                        notifyAll();
                        return returnedToken;
                    } else if (player instanceof SmartPlayer) {
                        for (Player otherPlayer : players) {
                            if (!otherPlayer.equals(player) && otherPlayer.checkAP() >= 2 && otherPlayer.checkAP() >= player.checkAP()) {
                                /*
                                Daca progresia celorlalti este mai mare de 2(ca sa poata exista), si daca progresia altui jucator este
                                mai mare decat progresia smartplayer-ului actual incearca sa-i ia urmatorul nr pentru progresie
                                */
                                List<Integer> otherPlayerRatioNumbers = otherPlayer.getMaxRatioNumbers();
                                Integer ratio = otherPlayerRatioNumbers.get(1) - otherPlayerRatioNumbers.get(0);
                                Integer lastNumber = otherPlayerRatioNumbers.get(otherPlayerRatioNumbers.size() - 1);
                                Integer firstNumber = otherPlayerRatioNumbers.get(0);
                                for (Token token : board) {
                                    if (token.getNumber() == firstNumber - ratio || token.getNumber() == lastNumber + ratio) {
                                        int indexOfToken = board.indexOf(token);
                                        Token returnedToken = board.get(indexOfToken);
                                        System.out.println(player.getName() + " a luat token-ul " + returnedToken.getNumber());
                                        board.remove(indexOfToken);
                                        notifyAll();
                                        return returnedToken;
                                    }
                                }
                            }
                        }
                        if (player.getTokenSet().size() >= 2) { //altfel, daca nu exista cui sa-i strice ratia  si daca smart player are o ratie, incearca sa-si extinda progresia
                            List<Integer> SmartPlayerRatioNumbers = player.getMaxRatioNumbers();
                            Integer ratio = SmartPlayerRatioNumbers.get(1) - SmartPlayerRatioNumbers.get(0);
                            Integer lastNumber = SmartPlayerRatioNumbers.get(SmartPlayerRatioNumbers.size() - 1);
                            Integer firstNumber = SmartPlayerRatioNumbers.get(0);
                            for (Token token : board) {
                                if (token.getNumber() == firstNumber - ratio || token.getNumber() == lastNumber + ratio) {
                                    int indexOfToken = board.indexOf(token);
                                    Token returnedToken = board.get(indexOfToken);
                                    System.out.println(player.getName() + " a luat token-ul " + returnedToken.getNumber());
                                    board.remove(indexOfToken);
                                    notifyAll();
                                    return returnedToken;
                                }
                            }

                        }
                        //nu exista nr pentru extinderea progresiei actuale
                        int randomIndex = Main.getRandomNumber(0, board.size() - 1);
                        Token returnedToken = board.get(randomIndex);
                        System.out.println(player.getName() + " a luat token-ul " + returnedToken.getNumber());
                        board.remove(randomIndex);
                        notifyAll();
                        return returnedToken;
                    }
                }
                gameOver = true;
                notifyAll();
                return new Token(maximumValue + 1);//Jocul s-a terminat deoarece s-au terminat token-urile
            } else {
                gameOver = true;
                notifyAll();
                return new Token(maximumValue + 2);//Ii instiintez si pe ceilalti jucatori
            }
        } else {
            gameOver = true;
            notifyAll();
            return new Token(maximumValue + 3);//Jocul s-a terminat deoarece a expirat timpul
        }

    }

}
