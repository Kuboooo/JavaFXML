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



    public void appendMessage(String message){
        System.out.println("Text area = " + textArea);
        textArea.appendText("\n"+message);
    }

    @FXML
    public String posliMasage(){

        return textFieldMessage.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("First text");
    }
}
