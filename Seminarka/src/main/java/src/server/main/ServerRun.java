package src.server.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerRun {

    private static final Logger logger = LoggerFactory.getLogger(ServerRun.class);
    private static List<String> connectionList;


    public static void main(String[] args) {
        logger.info("runnin main");

        ServerCommandProcessor.StartingThread startingThread;
        Thread thread;
        connectionList = new ArrayList<>();
        List<Game> gameList = new ArrayList<>();
        GameList.setGameList(gameList);

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                startingThread = new ServerCommandProcessor.StartingThread(serverSocket.accept());
                thread = new Thread(startingThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getConnectionList() {
        return connectionList;
    }

}
