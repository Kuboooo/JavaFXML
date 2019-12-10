package src.server.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerRun {

    private static final Logger logger = LoggerFactory.getLogger(ServerRun.class);

    public static void main(String[] args) {
        logger.info("runnin main");

        StartingThread startingThread;
        Thread thread;

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
                while (true) {
                    Game game = new Game();

                    startingThread = new StartingThread(game, serverSocket.accept(), "O");
                    thread = new Thread(startingThread);
                    thread.start();
                    game.setPlayerO(new Player(startingThread.getSocket()));
                    logger.info("First player O in");

                    startingThread = new StartingThread(game, serverSocket.accept(), "X");
                    thread = new Thread(startingThread);
                    thread.start();
                    game.setPlayerX(new Player(startingThread.getSocket()));
                    logger.info("Player X in, game can begin");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class StartingThread implements Runnable{

        public Socket playerSocket;
        private String name;
        private Game game;

        String messageToBeSent;


        public StartingThread(Game game,Socket socket, String name){
            this.game = game;
            this.playerSocket = socket;
            this.name = name;
        }

        @Override
        public void run() {
                PrintWriter output = null;
            BufferedReader input = null;
                try {
                    input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));

                    while (true) {
                        logger.info("cakam na spravu");
                        messageToBeSent = input.readLine();
                        logger.info("Player " + name + " sent " + messageToBeSent);

                        //TODO: poriesit ked sa jeden logne a druhy este nie
                        output = new PrintWriter(new OutputStreamWriter(game.getOpponentSocket(playerSocket).getOutputStream(), StandardCharsets.UTF_8), true);
                        output.println(messageToBeSent);
                        logger.info("sent message " + messageToBeSent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        private Socket getSocket() {
            return playerSocket;
        }
    }
}
