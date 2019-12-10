package src.client.main;

import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;
import src.server.main.ServerRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadingClass implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServerRun.class);

    Socket clientSocket;
    BufferedReader input;
    String inputMessage = "";
    private GameLayoutController glc;

    public ThreadingClass(Socket clientSocket, BufferedReader input) {
        this.clientSocket = clientSocket;
        this.input = input;
    }

    @Override
    public void run() {

            while (true) {
                try {
                    logger.info("Awaiting message");
                    inputMessage =
                            input.readLine();

                    logger.info("Received message: " + inputMessage);
                    logger.info("GLC UPDATED : " + glc + inputMessage);

                        if (startsWith1(inputMessage)) {
                            logger.info("this is a command " + inputMessage);
                            inputMessage = trimMessage(inputMessage);
                            logger.info("this is a command " + inputMessage);
                            glc.appendMessage(inputMessage);
                        }

                        glc.processCommand(inputMessage);

                    if (inputMessage.equals("bye")) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public GameLayoutController getGlc() {
        return glc;
    }

    public void setGlc(GameLayoutController glc) {
        this.glc = glc;
    }

    private boolean startsWith1(String s){
        return s.substring(0, 1).equals("1");
    }

    private String trimMessage(String s){
        return s.substring(1);
    }

}