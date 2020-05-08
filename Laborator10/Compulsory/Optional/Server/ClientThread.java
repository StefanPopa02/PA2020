package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientThread extends Thread {
    private Socket socket;
    private boolean stillCreatingGame;
    private volatile Map<Socket, Player> mapare = new HashMap<>();


    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (GameServer.isServerStillRunning()) {
                String command = input.readLine();
                System.out.println("Received client command: " + command);
                if (command.equals("exit")) {
                    break;
                } else if (command.equals("stop")) {
                    GameServer.setServerStillRunning(false);
                    output.println("Server stopped");
                    try (Socket poisonSocket = new Socket("localhost", 5000)) {
                        //Opresc starea blocanta din serversocket.accept();
                    } catch (IOException e) {
                        System.out.println("Eroare la creare poison pill");
                    }
                } else { //Comenzi
                    if (!GameServer.isServerStillRunning()) {
                        output.println("Server stopped");
                    } else {
                        if (command.startsWith("create game")) {
                            createGame(command);
                        } else if (command.startsWith("join game")) {
                            joinGame(command);
                        } else {
                            output.println("Server received the request: " + command);
                        }

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // Mai rar
                System.out.println(e.getMessage());
            }
        }
    }

    private void createGame(String command) {
        String roomName = command.substring(12);
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Game.getGame().addRoom(roomName, new ArrayList<>());
            output.println("Room " + roomName + " created --> Use join game <roomName> to enter");
        } catch (IOException e) {
            System.out.println("Oops..." + e.getMessage());
        }
    }


    private synchronized void joinGame(String command) {
        String chosenRoom = command.substring(10);
        Game game = Game.getGame();
        Map<String, List<Player>> rooms = game.getRooms();
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            if (rooms.keySet().contains(chosenRoom)) {
                String color = "";
                if (rooms.get(chosenRoom).isEmpty()) {
                    output.println("Choose your color(r or b)");
                    color = input.readLine();
                    if (color.equals("b")) {
                        color = "blue";
                    } else {
                        color = "red";
                    }
                    output.println("You are color " + color + " type go when you are ready");
                    rooms.get(chosenRoom).add(new Player(this.socket, color, 0));
//                    while (game.getRooms().get(chosenRoom).size() < 2) {
////                        System.out.println("Room size: "+game.getRooms().get(chosenRoom).size());
////                        try {
////                            wait();
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
//                    }
                    System.out.println("Am iesit din wait");
                    playGame(chosenRoom);
                } else {
                    String culoareAdversar = rooms.get(chosenRoom).get(0).getColor();
                    if (culoareAdversar.equals("blue")) {
                        color = "red";
                    } else {
                        color = "blue";
                    }
                    rooms.get(chosenRoom).add(new Player(this.socket, color, 1));
                    output.println("You are color " + color + " type go when you are ready");
                    notifyAll();
                    playGame(chosenRoom);
                }

            } else {
                output.println("Room doesn't exist!");
            }

        } catch (IOException e) {
            System.out.println("Oops..." + e.getMessage());
        }

    }

    private synchronized void playGame(String roomName) {
        List<Player> players = Game.getGame().getRooms().get(roomName);
        System.out.println("Socket-ul meu este: " + this.socket);
        System.out.println("Size camera: " + players.size());
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            boolean gameIsRunning = true;
            input.readLine();
            Player player1 = players.get(0);
            Player player2 = players.get(1);
            int tura;
            Board board = Board.getBoard();
            if (this.socket == player1.getSocket()) {
                tura = 0;
            } else {
                tura = 1;
            }
            System.out.println(this.socket + " are tura: " + tura);
            while (gameIsRunning) {
                while (tura != board.getTurnNumber()) {
                    //System.out.println(board.getTurnNumber());
                }
                System.out.println("Este tura lui: " + players.get(board.getTurnNumber()).getColor());
                String raspuns = raspunsMatrice(board.getMatrix());
                output.println(raspuns);
                String mutare = input.readLine();
                String linieString="";
                String coloanaString="";
                linieString+=mutare.charAt(0);
                coloanaString+=mutare.charAt(2);
                int linia = Integer.valueOf(linieString);
                int coloana = Integer.valueOf(coloanaString);
                board.submitMove(linia,coloana,getPlayerBySocket(players,this.socket).getColor());
                board.setTurnNumber(1 - tura);
                if(board.isGameOver()){
                    break;
                }
                System.out.println("Acum este tura: " + board.getTurnNumber());
            }
            output.println(board.getWinner());
        } catch (IOException e) {
            System.out.println("Oops..." + e.getMessage());
        }
    }


    private String raspunsMatrice(String[][] matrix){
        String raspuns = "";
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                    raspuns+=matrix[i][j]+ " ";
            }
            raspuns+="newline";
        }
        return raspuns;
    }
    private Player getPlayerBySocket(List<Player> players, Socket socket) {
        for (Player player : players) {
            if (socket == player.getSocket()) {
                return player;
            }
        }
        return null;
    }
}
