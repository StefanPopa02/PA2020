package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.Player;
import sample.model.DatabaseService;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField registerUsername;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private Button registerSignUpButton;

    @FXML
    private PasswordField registerRePassword;

    @FXML
    private TextField registerLastName;

    @FXML
    private TextField registerFirstName;

    @FXML
    private Button registerSignInButton;

    private DatabaseService databaseService;

    @FXML
    public void initialize() {
        registerSignUpButton.setDisable(true);
    }

    @FXML
    public void handleKeyReleased() {

        String firstNameText = registerFirstName.getText();
        String lastNameText = registerLastName.getText();
        String usernameText = registerUsername.getText();
        String passwordText = registerPassword.getText();
        String rePasswordText = registerRePassword.getText();
        boolean emptyText = usernameText.trim().isEmpty() || passwordText.trim().isEmpty() || firstNameText.trim().isEmpty()
                || lastNameText.trim().isEmpty() || rePasswordText.trim().isEmpty();
        boolean passwordsDontMatch = !(passwordText.equals(rePasswordText));
        boolean disableButtons = true;
        if (emptyText == false && passwordsDontMatch == false) {
            disableButtons = false;
        }
        registerSignUpButton.setDisable(disableButtons);
    }

    @FXML
    public void handleSignUp(){
        databaseService = new DatabaseService();
        String firstNameText = registerFirstName.getText();
        String lastNameText = registerLastName.getText();
        String usernameText = registerUsername.getText();
        String passwordText = registerPassword.getText();
        Player player = new Player(firstNameText,lastNameText,usernameText,passwordText);
        databaseService.insertPlayerToDb(player);
        goToSignIn();
    }

    @FXML
    public void goToSignIn() {
        registerSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/login.fxml"));

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
