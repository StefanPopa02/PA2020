package com.popastefan.dao;

import com.popastefan.datasource.Database;
import com.popastefan.model.Player;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class PlayerDataAccessService implements PlayerDao {
    private Connection con = Database.getMyDatabase().getCon();

    @Override
    public int insertPlayer(int id, Player player) {
        insertPlayerToDb(id, player);
        return 1;
    }

    private void insertPlayerToDb(int id, Player player) {
        String sql = "INSERT INTO player(id,name) VALUES(?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, player.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> selectAllPlayers() {
        return obtainAllPlayers();
    }

    public List<Player> obtainAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "Select * from player";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("id");
                String playerName = resultSet.getString("name");
                players.add(new Player(playerId, playerName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public int deletePlayerById(int id) {
        Optional<Player> chosenPlayer = selectPlayerById(id);
        if (chosenPlayer.isEmpty()) {
            return 0;
        }
        int idToRemove = chosenPlayer.get().getId();
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM player where id = " + idToRemove;
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    @Override
    public int updatePlayerById(int id, Player player) {
        String sql = "UPDATE player SET name = ? where id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,player.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    @Override
    public Optional<Player> selectPlayerById(int id) {
        return obtainAllPlayers()
                .stream()
                .filter(player -> player.getId() == id)
                .findFirst();
    }

}
