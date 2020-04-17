package repo;

import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private EntityManager entityManager;

    public ArtistRepository() {
        entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
    }

    public void create(ArtistsEntity artistsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(artistsEntity);
        entityManager.getTransaction().commit();
    }

    public ArtistsEntity findById(int id) {
        return entityManager.find(ArtistsEntity.class, id);
    }

    public List<ArtistsEntity> findByName(String name) {
        List artistsList;
        entityManager.getTransaction().begin();
        artistsList = entityManager.createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
        return artistsList;
    }

    public void close() {
        entityManager.close();
    }
}
