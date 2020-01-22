package client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectClient {

    private static final Logger logger = LoggerFactory.getLogger(ConnectClient.class);

    private static ConnectClient connectClient = null;
    private static Socket playerSocket;

    private ConnectClient() {
    }

    public static ConnectClient getInstance() {
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

    Socket getPlayerSocket() {
        return playerSocket;
    }
}


