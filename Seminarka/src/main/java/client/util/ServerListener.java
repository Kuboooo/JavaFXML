package client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ServerListener implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServerListener.class);

    private BufferedReader input;

    @Override
    public void run() {
        CommandReceiver commandReceiver = CommandReceiver.getInstance();

        try {
            input = new BufferedReader(new InputStreamReader(ConnectClient.getInstance().getPlayerSocket().getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputMessage;
        while (!ConnectClient.getInstance().getPlayerSocket().isClosed()) {
            try {
                logger.info("Awaiting message");
                inputMessage = input.readLine();
                logger.info("Received a message from server " + inputMessage);
                commandReceiver.process(inputMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}