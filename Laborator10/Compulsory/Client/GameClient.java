package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class GameClient {
    private boolean gameStillRunning = true;

    public void startGame() {
        try (Socket socket = new Socket("localhost", 5000)) {

            BufferedReader serverResponse = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter commandToSend = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String command;
            String response;

            do {
                System.out.println("Enter command: ");
                command = scanner.nextLine();

                commandToSend.println(command);
                if (command.equals("exit")) {
                    System.out.println("Closed connection");
                } else if (command.equals("stop")) {
                    response = serverResponse.readLine();
                    System.out.println(response);
                } else {
                    response = serverResponse.readLine();
                    if (response.equals("Server stopped")) {
                        System.out.println("Server stopped");
                        break;
                    }
                    System.out.println(response);
                }
            } while (!command.equals("exit") && !command.equals("stop"));
        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out");
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
