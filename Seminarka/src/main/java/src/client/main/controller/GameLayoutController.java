package src.client.main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class GameLayoutController implements Initializable, ControllerInterface {

    private static final Logger logger = LoggerFactory.getLogger(GameLayoutController.class);

    private String messsageTobeSent;

    @FXML
    private Label playerName;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button findOpponent;

    @FXML
    private TextArea textArea;

    @FXML
    private Button sendMessageChat;

    @FXML
    private TextField textFieldMessage;

    private Socket playerSocket;

    private PrintWriter output;


    public void appendMessage(String message){
        textArea.appendText("\n"+message);
    }

    @FXML
    public void posliMasage(){
        CommanderSender.getInstance().process(Commands.CHAT, textFieldMessage.getText());
        textFieldMessage.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (textArea.isVisible()) {
            textArea.setVisible(false);
            textFieldMessage.setVisible(false);
            sendMessageChat.setVisible(false);
            button1.setMouseTransparent(true);
            button2.setMouseTransparent(true);
            button3.setMouseTransparent(true);
            button4.setMouseTransparent(true);
            button5.setMouseTransparent(true);
            button6.setMouseTransparent(true);
            button7.setMouseTransparent(true);
            button8.setMouseTransparent(true);
            button9.setMouseTransparent(true);
        }else {
            textArea.setVisible(true);
            textFieldMessage.setVisible(true);
            sendMessageChat.setVisible(true);
            button1.setMouseTransparent(false);
            button2.setMouseTransparent(false);
            button3.setMouseTransparent(false);
            button4.setMouseTransparent(false);
            button5.setMouseTransparent(false);
            button6.setMouseTransparent(false);
            button7.setMouseTransparent(false);
            button8.setMouseTransparent(false);
            button9.setMouseTransparent(false);
        }
    }

    public void setUpForPlay(){
        textArea.setVisible(true);
        textFieldMessage.setVisible(true);
        sendMessageChat.setVisible(true);
        button1.setMouseTransparent(false);
        button2.setMouseTransparent(false);
        button3.setMouseTransparent(false);
        button4.setMouseTransparent(false);
        button5.setMouseTransparent(false);
        button6.setMouseTransparent(false);
        button7.setMouseTransparent(false);
        button8.setMouseTransparent(false);
        button9.setMouseTransparent(false);
    }

    @FXML
    public void findOpponent(ActionEvent event){

        loadUpLFOLayout(event);
        logger.info("we done in this window tho");
    }

    private void loadUpLFOLayout(ActionEvent event){

        Parent root;
        try {

           // Stage oldStage = (Stage) findOpponent.getScene().getWindow();
          //  oldStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LookingForOpponent.fxml"));

            root = loader.load();

            CommandReceiver.setCurrentControler(loader.getController());

            Stage stage = new Stage();
            stage.setTitle("Lookign 4 opponent title");
            stage.setScene(new Scene(root, 250, 150));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processCommand(String command){
        switch (command){
            case "f1":
                button1.setMouseTransparent(true);
                break;
            case "f2":
                button2.setMouseTransparent(true);
                break;
            case "f3":
                button3.setMouseTransparent(true);
                break;
            case "f4":
                button4.setMouseTransparent(true);
                break;
            case "f5":
                button5.setMouseTransparent(true);
                break;
            case "f6":
                button6.setMouseTransparent(true);
                break;
            case "f7":
                button7.setMouseTransparent(true);
                break;
            case "f8":
                button8.setMouseTransparent(true);
                break;
            case "f9":
                button9.setMouseTransparent(true);
                break;
            case "X":
                playerName.setText("Player: X");
                break;
            case "O":
                playerName.setText("Player: O");
                break;
                default:
                    logger.info("random default");
        }
    }

    @FXML
    public void button1Pressed(){
        output.println("f1");
        button1.setMouseTransparent(true);
    }

    @FXML
    public void button2Pressed(){
        output.println("f2");
        button2.setMouseTransparent(true);
    }

    @FXML
    public void button3Pressed(){
        output.println("f3");
        button3.setMouseTransparent(true);
    }

    @FXML
    public void button4Pressed(){
        output.println("f4");
        button4.setMouseTransparent(true);
    }

    @FXML
    public void button5Pressed(){
        output.println("f5");
        button5.setMouseTransparent(true);
    }

    @FXML
    public void button6Pressed(){
        output.println("f6");
        button6.setMouseTransparent(true);
    }

    @FXML
    public void button7Pressed(){
        output.println("f7");
        button7.setMouseTransparent(true);
    }

    @FXML
    public void button8Pressed(){
        output.println("f8");
        button8.setMouseTransparent(true);
    }

    @FXML
    public void button9Pressed(){
        output.println("f9");
        button9.setMouseTransparent(true);
    }

}
