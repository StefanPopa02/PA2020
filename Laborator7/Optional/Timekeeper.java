public class Timekeeper implements Runnable {
    private boolean gameFinished = false;
    private Board board;

    public Timekeeper(Board board) {
        this.board = board;
    }

    public boolean isTimeUp() {
        return gameFinished;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long timeElapsed = 0;
        while (timeElapsed < 60000 && !board.isGameOver()) {
            long finish = System.currentTimeMillis();
            timeElapsed = finish - start;
        }
        gameFinished = true;
    }
}
