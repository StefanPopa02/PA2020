public class Chart {
    private int id;
    private String albumName;
    private int albumId;
    private int chartNumber;

    public Chart(String albumName, int albumId, int chartNumber) {
        this.albumName = albumName;
        this.albumId = albumId;
        this.chartNumber = chartNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getChartNumber() {
        return chartNumber;
    }

    public void setChartNumber(int chartNumber) {
        this.chartNumber = chartNumber;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", albumId=" + albumId +
                ", chartNumber=" + chartNumber +
                '}';
    }
}
