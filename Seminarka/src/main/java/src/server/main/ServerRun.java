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
    private static List<StartingThread> connectionPool = new ArrayList<>();
    private static Player pl;

    public static void main(String[] args) {
        logger.info("runnin main");


        StartingThread startingThread;
        Thread thread;

        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            while (true){

                //logger.info("in server while loop");
                //Socket playerSocket = serverSocket.accept();
                //pl = new Player(playerSocket);
               // logger.info("Accepted player");

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
/*
                startingThread = new StartingThread(playerSocket);
                thread = new Thread(startingThread);

                connectionPool.add(startingThread);

                logger.info("starting thread");
                thread.start();
                logger.info("thred runnin");
                */
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
                while (true) {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));
                    System.out.println("cakam prvu spravu");
                    messageToBeSent = input.readLine();
                    logger.info("Player "+ name + " sent " + messageToBeSent);
                                output = new PrintWriter(new OutputStreamWriter(game.getOpponentSocket(playerSocket).getOutputStream(), StandardCharsets.UTF_8), true);
                                output.println(messageToBeSent);
                                logger.info("sent message " + messageToBeSent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private Socket getSocket() {
            return playerSocket;
        }
    }
}
