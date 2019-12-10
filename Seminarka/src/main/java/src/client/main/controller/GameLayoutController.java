package src.client.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.ThreadingClass;
import src.server.main.ServerRun;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class GameLayoutController implements Initializable{

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
    private TextArea textArea;

    @FXML
    private Button sendMessageChat;

    @FXML
    private TextField textFieldMessage;

    private Socket playerSocket;

    private PrintWriter output;


    public void appendMessage(String message){
        System.out.println("Text area = " + textArea);
        textArea.appendText("\n"+message);
    }

    @FXML
    public void posliMasage(){
        output.println("1" + textFieldMessage.getText());
        textFieldMessage.clear();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("First text");
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public void setPlayerSocket(Socket playerSocket) {
        this.playerSocket = playerSocket;
        try {
            output = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
        } catch (IOException e) {
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
}
