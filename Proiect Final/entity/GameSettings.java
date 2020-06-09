package sample.entity;

public class GameSettings {
    private static int category = 4;

    public static int getCategory() {
        return category;
    }

    public static void setCategory(int category) {
        GameSettings.category = category;
    }
}
