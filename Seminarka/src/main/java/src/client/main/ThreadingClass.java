package src.client.main;

import javafx.fxml.FXMLLoader;
import src.client.main.controller.GameLayoutController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadingClass implements Runnable {

    private GameLayoutController gml;

    public void setGml(GameLayoutController gml) {
        this.gml = gml;
    }

    Socket clientSocket;
    BufferedReader input;
    PrintWriter output;
    String outputMessage = "";
    String inputMessage = "";
    boolean hasName = false;

    public ThreadingClass(Socket clientSocket, BufferedReader input, PrintWriter output, GameLayoutController gml) {
        this.gml = gml;
        this.clientSocket = clientSocket;
        this.input = input;
        this.output = output;
        System.out.println(gml + " gml  z konstruktora");
        gml.gotMessage("Ja viem ze to nejde tho");
    }

    @Override
    public void run() {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("GameLayout.fxml"));
        //GameLayoutController gml = loader.<GameLayoutController>getController();
        //gml = new GameLayoutController();
        if (output!= null){
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                outputMessage = scanner.nextLine();
                output.println(outputMessage);
            }
        }else {
            while (true) {
                try {
                    inputMessage =
                            input.readLine();
                    if (inputMessage.equals("bye")) {
                        break;
                    }
                    System.out.println(inputMessage);
                    System.out.println(gml + " toto ej gml ");
                    gml.appendMessage(inputMessage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}