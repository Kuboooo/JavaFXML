package client.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.controllerInterface.ControllerInterface;
import client.util.CommandReceiver;
import client.util.CommanderSender;
import client.util.Commands;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ControllerInterface {

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
    public void logIn() {
        String name = usernameTextField.getText();
        logger.info("Username provided: " + name);
        CommanderSender.getInstance().process(Commands.SET_NAME, name);
    }

    public void errorLogin() {
        Platform.runLater(() -> {
            errorLabel.setVisible(true);
            errorLabel.setTextFill(Color.web("#f56c42"));
            errorLabel.setText("Username taken");
        });
    }

    public void loadUpGameLayout() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));

                    Parent loaderParent = loader.load();
                    CommandReceiver.getInstance().setCurrentControler(loader.getController());
                    Scene gameLayoutScene = new Scene(loaderParent);

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.setScene(gameLayoutScene);
                    window.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
