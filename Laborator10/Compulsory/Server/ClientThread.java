package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private boolean stillCreatingGame;

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
                        if (command.equals("create game")) {
                            createGame();
                        } else if (command.equals("join game")) {
                            joinGame();
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

    private void createGame() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            stillCreatingGame = true;
            output.println("Game created! Use join game to enter");
            while (stillCreatingGame) {
                String createGameInfo = input.readLine();
                System.out.println("Received createGame info: " + createGameInfo);
                if (createGameInfo.equals("exit")) {
                    output.println("Abort creating game...");
                    stillCreatingGame = false;
                    break;
                } else {
                    output.println("Comanda primita in createGame: " + createGameInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Oops..." + e.getMessage());
        }
    }


    private void joinGame() {
    }
}
