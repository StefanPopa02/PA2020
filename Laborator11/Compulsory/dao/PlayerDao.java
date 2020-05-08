package com.popastefan.dao;

import com.popastefan.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerDao {
    int insertPlayer(int id, Player player);

    default int insertPlayer(Player player) {
        int id = 0;
        return insertPlayer(id, player);
    }

    List<Player> selectAllPlayers();

    int deletePlayerById(int id);

    int updatePlayerById(int id, Player player);

    Optional<Player> selectPlayerById(int id);
}
