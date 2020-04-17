import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumController {
    private Connection con = Database.getMyDatabase().getCon();

    public void create(String name, int artistId, int releaseYear) {
        String sql = "INSERT INTO albums(name,artist_id,release_year) VALUES(?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3,releaseYear);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findByArtist(int artistId) {
        String sql = "Select * from albums where artist_id=?";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,artistId);
            ResultSet resultSet=pstmt.executeQuery();
            while(resultSet.next()){
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
    public int getAlbumId(String name){
        String sql = "Select * from albums where name=?";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet resultSet=pstmt.executeQuery();
            while(resultSet.next()){
                int rsId=resultSet.getInt("id");
                String rsName=resultSet.getString("name");
                int rsArtistId=resultSet.getInt("artist_id");
                int rsReleaseYear=resultSet.getInt("release_year");
                return rsId;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
