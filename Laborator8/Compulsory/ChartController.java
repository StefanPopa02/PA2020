import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartController {
    private Connection con = Database.getMyDatabase().getCon();
    public void create(String albumName, int albumId,int chartNumber){
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

    public void findByChartNumber(int chartNumber){
        String sql = "Select * from charts where chartNumber=? order by id";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,chartNumber);
            ResultSet resultSet=pstmt.executeQuery();
            while(resultSet.next()){
                //TODO
                int rsId=resultSet.getInt("id");
                String rsName=resultSet.getString("name");
                int rsArtistId=resultSet.getInt("artist_id");
                int rsReleaseYear=resultSet.getInt("release_year");
                System.out.println("Album "+rsId+"|"+rsName+"|"+rsArtistId+"|"+rsReleaseYear);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
