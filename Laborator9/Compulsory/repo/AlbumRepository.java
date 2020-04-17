package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    private EntityManager entityManager;

    public AlbumRepository() {
        entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
    }

    public void create(AlbumsEntity albumsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(albumsEntity);
        entityManager.getTransaction().commit();
    }

    public AlbumsEntity findById(int id) {
        return entityManager.find(AlbumsEntity.class, id);
    }

    public List<AlbumsEntity> findByName(String name) {
        List albumsList;
        entityManager.getTransaction().begin();
        albumsList = entityManager.createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
        return albumsList;
    }

    public List<AlbumsEntity> findByArtist(ArtistsEntity artist) {
        List albumsList;
        //entityManager.getTransaction().begin();
        albumsList = entityManager.createNamedQuery("Album.findByArtist").setParameter("artistId", artist.getId()).getResultList();
        entityManager.close();
        return albumsList;
    }

    public void close() {
        entityManager.close();
    }
}
