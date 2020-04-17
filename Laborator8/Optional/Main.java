import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Database myDb = Database.getMyDatabase();
        myDb.openDb();

        ArtistController artistController = new ArtistController();
        //artistController.create("Prznt","abc");
        //artistController.findByName("Prznt");
        AlbumController albumController = new AlbumController();
        // albumController.create("Stay for the View2",2,2020);
        //albumController.findByArtist(2);
        ChartController chartController = new ChartController();
        //chartController.findByChartNumber(1);
        FakeGenerator fakeGenerator = new FakeGenerator();
        List<Artist> artists = new ArrayList<Artist>();
        List<Album> albums = new ArrayList<Album>();
        List<Chart> charts = new ArrayList<Chart>();
        for(int i=0;i<30;i++){
            String name=fakeGenerator.getName();
            String country=fakeGenerator.getCountry();
            artists.add(new Artist(fakeGenerator.getName(),fakeGenerator.getCountry()));
            artistController.create(artists.get(artists.size()-1).getName(),artists.get(artists.size()-1).getCountry());
            artists.get(artists.size()-1).setId(artistController.getArtistId(artists.get(artists.size()-1).getName()));
            albums.add(new Album(fakeGenerator.getLastName(),artists.get(getRandomNumber(0,artists.size()-1)).getId(),getRandomNumber(1990,2020)));
            albumController.create(albums.get(albums.size()-1).getName(),albums.get(albums.size()-1).getArtistId(),albums.get(albums.size()-1).getReleaseYear());
            Album randomAlbum=albums.get(getRandomNumber(0,albums.size()-1));
            charts.add(new Chart(randomAlbum.getName(),albumController.getAlbumId(randomAlbum.getName()),getRandomNumber(1,5)));
            chartController.create(charts.get(charts.size()-1).getAlbumName(),charts.get(charts.size()-1).getAlbumId(),charts.get(charts.size()-1).getChartNumber());
            //artists.get(artists.size()-1).setId(artistController.getArtistId(name));
        }

        for(Artist artist:artists){
            System.out.println(artist);
        }
        for(Album album:albums){
            System.out.println(album);
        }
        for(Chart chart:charts){
            System.out.println(chart);
        }
        Ranking ranking = new Ranking();
        ranking.printRanking();

        myDb.closeDb();

    }
    private static int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
