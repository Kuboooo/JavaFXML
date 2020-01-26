package server.util;

import server.ServerRun;
import server.game.Game;
import server.game.GameList;
import server.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static server.util.Commands.*;


public class ServerCommandProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ServerCommandProcessor.class);
    private Socket currentPlayerSocket;
    private Game game;
    private Player currentPlayer;

    private ServerCommandProcessor(Socket currentPlayerSocket) {
        this.currentPlayerSocket = currentPlayerSocket;
    }

    private void process(String input) {
        PrintWriter outputCurrent = null;
        PrintWriter outputOpponent = null;
        try {
            outputCurrent = new PrintWriter(new OutputStreamWriter(currentPlayerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Commands command = findCommand(input);
        String inputFromPlayer = cutCommand(input, command);

        switch (command) {
            case SET_NAME:
                if (validName(inputFromPlayer)) {
                    String s = SET_NAME + "ok" + inputFromPlayer;
                    currentPlayer = new Player(currentPlayerSocket);
                    currentPlayer.setPlayerName(inputFromPlayer);
                    ServerRun.getConnectionList().add(currentPlayer.getPlayerName());
                    outputCurrent.println(SET_NAME + "ok" + inputFromPlayer);
                    logger.debug(outputCurrent + " output  current");
                    logger.info("returning " + s);
                } else {
                    outputCurrent.println(SET_NAME + "Name already taken" + inputFromPlayer);
                }
                break;
            case CANCEL:
                game.cancelPlayer(currentPlayer);
                outputCurrent.println(CANCEL);
                break;
            case CHAT:
                try {
                    outputOpponent = new PrintWriter(new OutputStreamWriter(game.getOpponentSocket(currentPlayerSocket).getOutputStream(), StandardCharsets.UTF_8), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputCurrent.println(CHAT + inputFromPlayer);
                outputOpponent.println(CHAT + inputFromPlayer);
                break;
            case FIND_OPPONENT:
                this.findOpponent();
                break;
            case LOGIN:
                break;
            case QUIT:
                try {
                    outputCurrent.println(QUIT);
                    logger.debug("Player left, closing connection");
                    currentPlayerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MOVE:
                try {
                    outputOpponent = new PrintWriter(new OutputStreamWriter(game.getOpponentSocket(currentPlayerSocket).getOutputStream(), StandardCharsets.UTF_8), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputCurrent.println(MOVE + inputFromPlayer + currentPlayer.getToken());
                outputOpponent.println(MOVE + inputFromPlayer + currentPlayer.getToken());
                break;
            default:
                break;
        }
    }

    private static synchronized boolean validName(String s) {
        return ServerRun.getConnectionList().stream().noneMatch(e -> e.equals(s));
    }

    private void findOpponent() {
        if (GameList.getGameList().isEmpty() || GameList.getGameList().get(GameList.getGameList().size() - 1).isFull()) {
            logger.debug("Game initialized");
            game = new Game();
            game.setPlayer(currentPlayer);
            GameList.getGameList().add(game);
        } else {
            logger.debug("Adding second player to game");
            game = GameList.getGameList().get(GameList.getGameList().size() - 1);
            game.setPlayer(currentPlayer);
            PrintWriter outputCurrent = null;
            PrintWriter outputOpponent = null;
            try {
                outputCurrent = new PrintWriter(new OutputStreamWriter(currentPlayerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
                outputOpponent = new PrintWriter(new OutputStreamWriter(game.getOpponentSocket(currentPlayerSocket).getOutputStream(), StandardCharsets.UTF_8), true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.debug("Updating first player");
            logger.debug("Sending message: " + FIND_OPPONENT + currentPlayer.getToken());
            outputCurrent.println(FIND_OPPONENT + currentPlayer.getToken() + game.getOpponent(currentPlayerSocket).getPlayerName());

            logger.debug("Updating second player");
            outputOpponent.println(FIND_OPPONENT + game.getOpponent(currentPlayerSocket).getToken() + currentPlayer.getPlayerName());
        }
    }

    private Commands findCommand(String input) {
        logger.info("Going through commands input: " + input);
        for (Commands command : Commands.values()) {
            logger.debug("Command: " + command.getCommand());
            if (input.toLowerCase().startsWith(command.getCommand())) {
                logger.debug(String.format("Found Command that fits: %s", command));
                return command;
            }
        }
        logger.debug("No commands, throwing null");
        return null;
    }

    private String cutCommand(String combo, Commands commands) {

        return combo.substring(commands.getCommand().length());
    }

    public static class StartingThread implements Runnable {

        String inputFromPlayer;
        private Socket playerSocket;


        public StartingThread(Socket socket) {
            this.playerSocket = socket;
        }

        @Override
        public void run() {
            BufferedReader input;
            ServerCommandProcessor serverCommandProcessor = new ServerCommandProcessor(playerSocket);
            try {
                input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));

                while (!playerSocket.isClosed()) {
                    logger.debug("Awaiting new message");
                    inputFromPlayer = input.readLine();
                    serverCommandProcessor.process(inputFromPlayer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
