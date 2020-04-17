import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ranking {
    private Connection con = Database.getMyDatabase().getCon();
    private List<String> top = new ArrayList<String>();

    public void printRanking() {
        String sql = "select art.name from artists art join albums alb on art.id=alb.artist_id join charts cha on alb.id=cha.album_id order by chart_number desc";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String artistName = resultSet.getString("name");
                if (!isAlreadyInTop(artistName)) {
                    top.add(artistName);
                }
            }
            System.out.println(top);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isAlreadyInTop(String name) {
        for (String string : top) {
            if (string.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
