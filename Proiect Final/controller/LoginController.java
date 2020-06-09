package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shaker;
import sample.entity.Player;
import sample.model.DatabaseService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private DatabaseService databaseService;

    @FXML
    void initialize() {
        loginSignInButton.setDisable(true);
    }

    @FXML
    public void handleKeyReleased() {

        String usernameText = loginUsername.getText();
        String passwordText = loginPassword.getText();
        boolean disableButtons = usernameText.trim().isEmpty() || passwordText.trim().isEmpty();
        loginSignInButton.setDisable(disableButtons);
    }

    @FXML
    public void handleSignIn() {
        databaseService = new DatabaseService();
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        boolean loginSuccess = databaseService.verifyPlayer(new Player(username, password));
        if (loginSuccess) {
            goToMainMenu();
        } else {
            Shaker usernameShaker = new Shaker(loginUsername);
            Shaker passwordShaker = new Shaker(loginPassword);
            usernameShaker.shake();
            passwordShaker.shake();
        }
    }

    @FXML
    public void goToSignUp() {
        loginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/register.fxml"));

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
        loginSignUpButton.getScene().getWindow().hide();
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