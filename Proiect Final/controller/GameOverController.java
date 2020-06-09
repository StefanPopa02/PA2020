package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.entity.Player;
import sample.entity.Ranking;
import sample.model.DatabaseService;

import java.awt.*;
import java.io.IOException;

public class GameOverController {

    @FXML
    private Button gameOverPlayAgain;

    @FXML
    private Button gameOverMainMenu;

    @FXML
    private Label gameOverCurrentPoints;

    @FXML
    private Label gameOverTotalPoints;

    @FXML
    private ListView<Ranking> gameOverRanking;

    private PointsController pointsController;
    private DatabaseService databaseService;
    private Ranking ranking;

    public void transferPoints(PointsController pointsController) {
        databaseService = new DatabaseService();
        this.pointsController = pointsController;
        gameOverCurrentPoints.setText(pointsController.getPoints().toString());
        databaseService.getData();
        Player.addPunctaj(pointsController.getPoints());
        gameOverTotalPoints.setText(Player.getPunctaj().toString());
        Player.addRaspCorecte(pointsController.getCorrectAnswers());
        Player.addRaspGresite(pointsController.getWrongAnswers());
        databaseService.updateData();
        ranking = new Ranking();
        ranking.setPunctaje(databaseService.makeRanking());
        gameOverRanking.getItems().add(ranking);
    }

    public void initialize(){

    }

    @FXML
    public void playGame() {
        gameOverCurrentPoints.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/game.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void goToMainMenu() {
        gameOverTotalPoints.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/mainMenu.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
