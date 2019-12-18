package src.client.main.controller;

import javafx.fxml.FXML;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controllerInterface.ControllerInterface;
import src.client.main.util.CommandReceiver;
import src.client.main.util.CommanderSender;
import src.client.main.util.Commands;

import java.net.URL;
import java.util.ResourceBundle;

public class LookingForOpponentController implements Initializable, ControllerInterface {
    private static final Logger logger = LoggerFactory.getLogger(LookingForOpponentController.class);


    @FXML
    private Button cancelSearching;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void cancelSearching(){
        Stage oldStage = (Stage) cancelSearching.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));

        GameLayoutController glc = loader.getController();
        //glc.setUpForPlay();
        CommandReceiver.setCurrentControler(glc);
        logger.info("setting glc: " + glc);
        //Stage olderStage = (Stage) ((Stage) cancelSearching.getScene().getWindow()).initModality();

        CommanderSender.getInstance().process(Commands.CANCEL, null);

        oldStage.close();
    }

    public void opponentFound(){
        //Stage oldStage = (Stage) cancelSearching.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));
        GameLayoutController glc = loader.getController();
        //glc.setUpForPlay();


        //textArea.setMouseTransparent(false

        CommandReceiver.setCurrentControler(loader.getController());
        //oldStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommanderSender.getInstance().process(Commands.FIND_OPPONENT, null);
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
    }
}
