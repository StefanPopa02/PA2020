package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private static boolean serverStillRunning = true;
    private List<ClientThread> clientThreadList = new ArrayList<>();

    public void startGame() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (serverStillRunning) {
                clientThreadList.add(new ClientThread(serverSocket.accept()));
                clientThreadList.get(clientThreadList.size() - 1).start();
            }
            clientThreadList.remove(clientThreadList.size() - 1);//Elimin poison pill
            for (ClientThread clientThread : clientThreadList) {
                try {
                    clientThread.join();
                } catch (InterruptedException e) {
                    System.out.println("Eroare la asteptarea thread-urilor");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }

    public static boolean isServerStillRunning() {
        return serverStillRunning;
    }

    public static void setServerStillRunning(boolean serverStillRunning) {
        GameServer.serverStillRunning = serverStillRunning;
    }
}
