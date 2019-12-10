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
    PrintWriter output;
    String outputMessage = "";
    String inputMessage = "";

    public ThreadingClass(Socket clientSocket, BufferedReader input, PrintWriter output) {
        this.clientSocket = clientSocket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        if (output!= null){
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                logger.info("Ready to send message");
                outputMessage = scanner.nextLine();
                output.println(outputMessage);
                logger.info(outputMessage + " message sent");
            }
        }else {
            while (true) {
                try {
                    logger.info("Awaiting message");
                    inputMessage =
                            input.readLine();
                    logger.info("Received message: " + inputMessage);

                    if (inputMessage.equals("bye")) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}