package src.client.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.ClientRun;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(ClientRun.class);

    @FXML
    private Button buttonLogin;

    private GameLayoutController gml;

    public void logPlayerIn(){
        Parent root;
        try {
            //root = FXMLLoader.load(getClass().getClassLoader().getResource("GameLayout.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameLayout.fxml"));
            gml = new GameLayoutController();
            root = loader.load();
            loader.setController(gml);



            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public LoginController(){}

    public String checkLogin(String name){

        logger.info("Name inserted: " + name);
        logger.info("length: " + name.length());

        if (name.length() != 0){
            if (!checkNameExists(name)){

                return "Welcome to game " + name;
            }
            return "This name already exists";
        }

        return "Please enter a name";
    }

    public boolean checkNameExists(String name){

        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
