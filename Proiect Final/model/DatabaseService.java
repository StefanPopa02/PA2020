package sample.model;

import sample.database.Database;
import sample.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private Connection con = Database.getMyDatabase().getCon();

    public void insertPlayerToDb(Player player) {
        String sql = "INSERT INTO utilizatori(firstName,lastName,username,password) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, player.getFirstName());
            pstmt.setString(2, player.getLastName());
            pstmt.setString(3, player.getUsername());
            pstmt.setString(4, player.getPassword());
            pstmt.executeUpdate();
            verifyPlayer(player);
            insertPlayerToPunctaje();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserFName(){
        String sql = "select firstName from utilizatori where id = ?";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,Player.getId());
            ResultSet resultSet = pstmt.executeQuery();
            String fName="";
            while(resultSet.next()){
                fName = resultSet.getString("firstName");
            }
            return fName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<String> makeRanking() {
        String sql = "select username,punctaj from utilizatori natural join punctaje order by punctaj desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            List<String> punctaje = new ArrayList<>();
            while(resultSet.next()){
                String username = resultSet.getString("username");
                int punctaj = resultSet.getInt("punctaj");
                punctaje.add(username+": "+punctaj);
            }
            return punctaje;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertPlayerToPunctaje() {
        String sql = "INSERT INTO punctaje(id,punctaj,rasp_corecte,rasp_gresite) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Player.getId());
            pstmt.setInt(2, Player.getPunctaj());
            pstmt.setInt(3, Player.getRaspCorecte());
            pstmt.setInt(4, Player.getRaspGresite());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        String sql = "SELECT * from punctaje where id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Player.getId().toString());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int punctaj = resultSet.getInt("punctaj");
                Player.setPunctaj(punctaj);
                int raspCorecte = resultSet.getInt("rasp_corecte");
                Player.setRaspCorecte(raspCorecte);
                int raspGresite = resultSet.getInt("rasp_gresite");
                Player.setRaspGresite(raspGresite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        String sql = " Update punctaje set punctaj=? , rasp_corecte=? , rasp_gresite=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int punctaj = Player.getPunctaj();
            int rasp_corecte = Player.getRaspCorecte();
            int rasp_gresite = Player.getRaspGresite();
            int id = Player.getId();
            preparedStatement.setInt(1, punctaj);
            preparedStatement.setInt(2, rasp_corecte);
            preparedStatement.setInt(3, rasp_gresite);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyPlayer(Player player) {
        String sql = "SELECT id from utilizatori where username=? and password=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, player.getUsername());
            pstmt.setString(2, player.getPassword());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Player.setId(id);
            }
            if (Player.getId() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
