package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.entity.GameSettings;
import sample.entity.Player;
import sample.model.DatabaseService;

import java.io.IOException;
import java.math.BigDecimal;

public class MainMenuController {

    @FXML
    private Label mainMenuFName;

    @FXML
    private Label mainMenuTotalPoints;

    @FXML
    private Label mainMenuCorrectAnswers;

    @FXML
    private Label mainMenuWrongAnswers;

    @FXML
    private Label mainMenuAccuracy;

    @FXML
    private Button category4;

    @FXML
    private Button category3;

    @FXML
    private Button category2;

    private DatabaseService databaseService;

    public void initialize() {
        listenForNewGame();
        databaseService = new DatabaseService();
        databaseService.getData();
        mainMenuTotalPoints.setText(Player.getPunctaj().toString());
        mainMenuCorrectAnswers.setText(Player.getRaspCorecte().toString());
        mainMenuWrongAnswers.setText(Player.getRaspGresite().toString());
        float accuracy = 0;
        if(Player.getRaspCorecte()>0) {
            accuracy = calculateAccuracy();
        }
        mainMenuAccuracy.setText(String.valueOf(accuracy));
        mainMenuFName.setText(databaseService.getUserFName());
    }

    private float calculateAccuracy() {
        float wrongA = Float.valueOf(Player.getRaspGresite());
        float correctA = Float.valueOf(Player.getRaspCorecte());
        float sum = correctA + wrongA;
        float result = correctA*100/sum;
        result = round(result,2);
        return result;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private void listenForNewGame() {
        EventHandler<ActionEvent> buttonHandler = event -> {
            Button btnPressed = ((Button) event.getSource());
            System.out.println("Buton apasat " + btnPressed);
            GameSettings.setCategory(btnPressed.getId().charAt(btnPressed.getId().length() - 1) - 48);
            System.out.println("categoria este: " + GameSettings.getCategory());
            playGame();
        };
        category4.setOnAction(buttonHandler);
        category3.setOnAction(buttonHandler);
        category2.setOnAction(buttonHandler);
    }

    @FXML
    public void playGame() {
        category4.getScene().getWindow().hide();
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

}

