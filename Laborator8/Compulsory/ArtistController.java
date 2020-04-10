import java.sql.*;

public class ArtistController {
    private Connection con = Database.getMyDatabase().getCon();

    public void create(String name, String country) {
        String sql = "INSERT INTO artists(name,country) VALUES(?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findByName(String name) {
        String sql = "Select * from artists where name=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int artistId = resultSet.getInt("id");
                String rsName = resultSet.getString("name");
                String rsCountry = resultSet.getString("country");
                System.out.println("Artist: "+artistId + "|" + rsName + "|" + rsCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
