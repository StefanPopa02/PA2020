import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartController {
    private Connection con = Database.getMyDatabase().getCon();

    public void create(String albumName, int albumId, int chartNumber) {
        String sql = "INSERT INTO charts(album_name,album_id,chart_number) VALUES(?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, albumName);
            pstmt.setInt(2, albumId);
            pstmt.setInt(3, chartNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findByChartNumber(int chartNumber) {
        String sql = "Select * from charts where chart_number=? order by id";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, chartNumber);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int rsId = resultSet.getInt("id");
                String rsAlbumName = resultSet.getString("album_name");
                int rsAlbumId = resultSet.getInt("album_id");
                int rsChartNumber = resultSet.getInt("chart_number");
                System.out.println(rsId + "|" + rsAlbumName + "|" + rsAlbumId + "|" + rsChartNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
