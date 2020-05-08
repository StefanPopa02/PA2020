package com.company;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private volatile static Map<String, List<Player>> rooms;
    private static Game game = null;

    private Game() {

    }

    public static Game getGame() {
        if (game == null) {
            game = new Game();
            rooms = new HashMap<>();
        }
        return game;
    }

    public synchronized Map<String, List<Player>> getRooms() {
        return rooms;
    }

    public synchronized void addRoom(String roomName, List<Player> players) {
        rooms.put(roomName, players);
    }
}
