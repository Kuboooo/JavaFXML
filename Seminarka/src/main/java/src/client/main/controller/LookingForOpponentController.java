package src.client.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import src.client.main.util.Commands;
import src.client.main.util.CommanderSender;

import java.net.URL;
import java.util.ResourceBundle;

public class LookingForOpponentController implements Initializable {

    @FXML
    private Button cancelSearching;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void cancelSearching(){
        Stage oldStage = (Stage) cancelSearching.getScene().getWindow();
        oldStage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommanderSender.getInstance().process(Commands.PLAY, null);
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
    }
}
