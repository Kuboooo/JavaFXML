package client.util;

import client.game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static client.util.Commands.*;


public class CommanderSender {
    private static final Logger logger = LoggerFactory.getLogger(CommanderSender.class);

    private static PrintWriter output;
    private static CommanderSender commanderSender = null;

    private CommanderSender() {
    }

    public static CommanderSender getInstance() {
        if (commanderSender == null) {
            commanderSender = new CommanderSender();
            try {
                output = new PrintWriter(new OutputStreamWriter(ConnectClient.getInstance().getPlayerSocket().getOutputStream(), StandardCharsets.UTF_8), true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return commanderSender;
    }

    public void process(Commands command, String value) {

        logger.info("got command here: " + command);
        logger.info("also got message: " + value);

        switch (command) {
            case FIND_OPPONENT:
                output.println(FIND_OPPONENT);
                break;
            case CHAT:
                output.println(CHAT + String.valueOf(Game.getCurrentPlayer().getToken()) + value);
                break;
            case SET_NAME:
                output.println(SET_NAME + value);
                logger.info("Sending server cmmand SET_NAME");
                break;
            case CANCEL:
                output.println(CANCEL);
                break;
            case QUIT:
                logger.debug("Leaving, sending quit command to server");
                output.println(QUIT + value);
            default:
                logger.debug("unknown command");
        }
    }

    public void process(Commands command, int value) {
        logger.info("got command here: " + command);
        logger.info("also got message: " + value);
        output.println(command + "" + value);
    }
}
