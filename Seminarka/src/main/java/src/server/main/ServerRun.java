package src.server.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServerRun {

    private static final Logger logger = LoggerFactory.getLogger(ServerRun.class);
    private static List<Player> connectionList;


    public static void main(String[] args) {
        logger.info("runnin main");

        StartingThread startingThread;
        Thread thread;
        connectionList = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
                while (true) {
                    Game game = new Game();

                    startingThread = new StartingThread(game, serverSocket.accept(), "O");
                    thread = new Thread(startingThread);
                    thread.start();

                    Player p1 = new Player(startingThread.getSocket());

                    game.setPlayerO(p1);
                    logger.info("First player O in");
                    connectionList.add(p1);


                    startingThread = new StartingThread(game, serverSocket.accept(), "X");

                    Player p2 = new Player(startingThread.getSocket());

                    thread = new Thread(startingThread);
                    thread.start();
                    game.setPlayerX(p2);
                    logger.info("Player X in, game can begin");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class StartingThread implements Runnable{

        public Socket playerSocket;
        private String token;
        private String playerName;
        private Game game;

        String messageToBeSent;


        public StartingThread(Game game,Socket socket, String token){
            this.game = game;
            this.playerSocket = socket;
            this.token = token;
        }

        @Override
        public void run() {
                PrintWriter output = null;
            BufferedReader input = null;
                try {
                    input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));

                    while (playerName == null) {
                        logger.info("server caka msg");
                        messageToBeSent = input.readLine();
                        output = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
                        logger.info("server dostal jmeno " + messageToBeSent);
                        if (validName(messageToBeSent)) {
                            logger.info("meno validne");
                            playerName = messageToBeSent;

                            output.println(playerName);
                        }else{
                            logger.info("name already taken " + messageToBeSent);
                            output.println("Name already taken");
                        }
                    }

                    while (true) {
                        logger.info("cakam na spravu");
                        messageToBeSent = input.readLine();
                        logger.info("Player " + token + " sent " + messageToBeSent);

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

        private boolean validName(String s) {
            return connectionList.stream().filter(e -> e.getPlayerName() != null).noneMatch(e -> e.getPlayerName().equals(s));
        }
    }
}
