package src.client.main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.util.CommanderSender;
import src.client.main.util.Commands;
import src.client.main.util.UsernameChecker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label errorLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setText("Submit");
        usernameTextField.setPromptText("Username");
        errorLabel.setVisible(false);
    }

    @FXML
    public void logIn(ActionEvent event){
        String name = usernameTextField.getText();
        logger.info("Username provided: " + name);
        CommanderSender.getInstance().process(Commands.SET_NAME, name);
    }

    public void errorLogin(){
        errorLabel.setVisible(true);
        errorLabel.setTextFill(Color.web("#f56c42"));
        errorLabel.setText("Username taken");
    }

    public void loadUpGameLayout(){

        try {
            Parent loaderParent = FXMLLoader.load(getClass().getResource("/GameLayout.fxml"));
            Scene gameLaoutScene = new Scene(loaderParent);

            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(gameLaoutScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
