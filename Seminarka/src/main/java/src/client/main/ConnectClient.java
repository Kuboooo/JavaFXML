package src.client.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;
import src.client.main.controllerInterface.ControllerInterface;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectClient {

    private static final Logger logger = LoggerFactory.getLogger(ConnectClient.class);

    private static ConnectClient connectClient = null;

    private ConnectClient(){}

    private static Socket playerSocket;

    public static ConnectClient getInstance(){
        if (connectClient == null) {
            connectClient = new ConnectClient();
            try {
                logger.info("Connecting to server");
                InetAddress address = InetAddress.getByName("127.0.0.1");
                playerSocket = new Socket(address, 8000);
                logger.debug("Connection successful");
            } catch (IOException ex) {
                logger.error("Socket exception: " + ex);
            }
        }
        return connectClient;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }
}


