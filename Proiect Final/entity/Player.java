package sample.entity;

public class Player {
    private static Integer id = 0;
    private static Integer punctaj = 0;
    private static Integer raspCorecte = 0;
    private static Integer raspGresite = 0;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public static void addPunctaj(int puncte) {
        punctaj += puncte;
    }

    public static void addRaspCorecte(int raspCorecte1) {
        raspCorecte += raspCorecte1;
    }

    public static void addRaspGresite(int raspGresite1) {
        raspGresite += raspGresite1;
    }

    public Player(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public static Integer getPunctaj() {
        return punctaj;
    }

    public static void setPunctaj(Integer punctaj) {
        Player.punctaj = punctaj;
    }

    public static Integer getRaspCorecte() {
        return raspCorecte;
    }

    public static void setRaspCorecte(Integer raspCorecte) {
        Player.raspCorecte = raspCorecte;
    }

    public static Integer getRaspGresite() {
        return raspGresite;
    }

    public static void setRaspGresite(Integer raspGresite) {
        Player.raspGresite = raspGresite;
    }


    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Player.id = id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
