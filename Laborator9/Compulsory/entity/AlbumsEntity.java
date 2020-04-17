package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "albums", schema = "MyAlbumsPU")
@NamedQueries({
        @NamedQuery(name = "Album.findByName",
                query = "SELECT album FROM AlbumsEntity album where album.name=:name"),
        @NamedQuery(name = "Album.findByArtist",
                query = "SELECT album FROM AlbumsEntity album where album.artistId=:artistId")
})
public class AlbumsEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;
    private String name;
    private int artistId;
    private Integer releaseYear;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "artist_id")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "release_year")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumsEntity that = (AlbumsEntity) o;

        if (id != that.id) return false;
        if (artistId != that.artistId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + artistId;
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AlbumsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
