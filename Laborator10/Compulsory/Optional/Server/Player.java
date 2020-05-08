package com.company;

import java.net.Socket;

public class Player {
    private Socket socket;
    private String color;
    private int turnNumber;

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Player(Socket socket, String color,int turnNumber) {
        this.socket = socket;
        this.color = color;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player{" +
                "socket=" + socket +
                ", color='" + color + '\'' +
                ", turnNumber=" + turnNumber +
                '}';
    }
}
