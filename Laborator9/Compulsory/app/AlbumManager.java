package app;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import repo.AlbumRepository;
import repo.ArtistRepository;

import java.util.List;

public class AlbumManager {
    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepository();
        AlbumRepository albumRepository = new AlbumRepository();
        ArtistsEntity artistsEntity1 = new ArtistsEntity();
        artistsEntity1.setName("test2");
        artistsEntity1.setCountry("Anglia");
        artistRepository.create(artistsEntity1);
        ArtistsEntity foundArtist = artistRepository.findById(0);
        System.out.println("findById artist: "+foundArtist);
        AlbumsEntity albumsEntity1 = new AlbumsEntity();
        albumsEntity1.setName("album2");
        albumsEntity1.setReleaseYear(2019);
        albumRepository.create(albumsEntity1);
        albumRepository.findById(0);
        AlbumsEntity foundAlbum = albumRepository.findById(0);
        System.out.println("findById album: "+foundAlbum);
        List<ArtistsEntity> artistsList = artistRepository.findByName("test2");
        System.out.println("findByName artist: "+artistsList);
        List<AlbumsEntity> albumsList = albumRepository.findByName("album2");
        System.out.println("findByName album: "+albumsList);
        List<AlbumsEntity> artistsList2 = albumRepository.findByArtist(artistsEntity1);
        System.out.println("findByArtist album:" +artistsList2);
        artistRepository.close();
        albumRepository.close();
    }
}