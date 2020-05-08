package com.company;

public class Board {
    private volatile int turnNumber;
    private volatile String[][] matrix;
    private static Board board = null;
    private static boolean gameOver;
    private String winner;
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private Board() {

    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
            board.setGameOver(false);
            board.setTurnNumber(0);
            String[][] matrix = new String[11][11];
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    matrix[i][j] = "e";
                }
            }
            board.setMatrix(matrix);
        }
        return board;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public synchronized String startGame(int playerTurnNumber) {
        String hello = "hello";
        while (this.turnNumber != playerTurnNumber) {
        }
        setTurnNumber(1 - turnNumber);
        return hello;
    }

    public void submitMove(int linia, int coloana, String color) {
        if(color.equals("blue")){
            color="b";
        }else{
            color="r";
        }
        checkIfGameOver();
        if (!gameOver) {
            this.matrix[linia][coloana] = color;
        }
    }

    public void checkIfGameOver() {
        if (checkRows() == true || checkColumns() == true) {
            setGameOver(true);
        }
    }

    private boolean checkRows() {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11 - 3; col++) {
                String element = this.matrix[row][col];
                if (element.equals("b") || element.equals("r")) {
                    if (element.equals(this.matrix[row][col + 1]) &&
                            element.equals(this.matrix[row][col + 2]) &&
                            element.equals(this.matrix[row][col + 3])) {
                        setWinner(element);
                        if(element.equals("b")){
                            winner="Blue";
                        }else{
                            winner="Red";
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setWinner(String element) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                this.matrix[i][j] = element;
            }
        }
    }

    private boolean checkColumns() {
        for (int row = 0; row < 11 - 3; row++) {
            for (int col = 0; col < 11; col++) {
                String element = this.matrix[row][col];
                if (element.equals("b") || element.equals("r")) {
                    if (element.equals(this.matrix[row + 1][col]) &&
                            element.equals(this.matrix[row + 2][col]) &&
                            element.equals(this.matrix[row + 3][col])) {
                        setWinner(element);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getWinner() {
        return winner+" a castigat!";
    }
}
