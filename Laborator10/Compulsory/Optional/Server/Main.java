package com.company;

import java.io.IOException;
import java.net.ServerSocket;

//Lipseste HTML representation
public class Main {

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.startGame();
    }
}
