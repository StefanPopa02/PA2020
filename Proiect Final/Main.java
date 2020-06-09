package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database.getMyDatabase().openDb();
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Database.getMyDatabase().closeDb();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
