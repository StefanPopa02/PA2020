import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Database myDb= Database.getMyDatabase();
        myDb.openDb();

        ArtistController artistController = new ArtistController();
        //artistController.create("Prznt","abc");
        artistController.findByName("Prznt");
        AlbumController albumController =new AlbumController();
       // albumController.create("Stay for the View2",2,2020);
        albumController.findByArtist(2);


        myDb.closeDb();

    }
}
