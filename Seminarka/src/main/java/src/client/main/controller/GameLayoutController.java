package src.client.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.client.main.ThreadingClass;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class GameLayoutController implements Initializable{

    private String messsageTobeSent;

    @FXML
    private TextArea textArea;

    @FXML
    private Button sendMessageChat;

    @FXML
    private TextField textFieldMessage;

    private Socket playerSocket;

    public void setPlayerSocket(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    private ThreadingClass tc;
/*
    @FXML
    public void posliMasage(){
        messsageTobeSent = textFieldMessage.getText();

    }
*/
    public void appendMessage(String message){
        System.out.println("Text area = " + textArea);
        textArea.appendText("\n"+message);
    }


    public void gotMessage(String s){
        System.out.println(" go t S from you " + s);
    }

    @FXML
    public String posliMasage(){
        System.out.println("Text area = " + textArea + " ??? "  + textFieldMessage.getText());
        DataOutputStream toServer = null;
        PrintWriter output = null;

        System.out.println( "Real GML: " + this);
/*
        try {
            System.out.println(" player socket " + playerSocket);
            toServer = new DataOutputStream(playerSocket.getOutputStream());
            System.out.println(" to server " + toServer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("messg to be sent: " + textFieldMessage.getText());
            toServer.writeUTF(textFieldMessage.getText());
            output = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            output.print(textFieldMessage.getText());
            System.out.println("sent msg : " + textFieldMessage.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return textFieldMessage.getText();

    }

    public void random() {


    Button btn = new Button();
    btn.applyCss();
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("First text");
//        tc.setGml(this);
    }
}
