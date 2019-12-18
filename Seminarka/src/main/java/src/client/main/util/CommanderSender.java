package src.client.main.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.ConnectClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static src.client.main.util.Commands.CANCEL;
import static src.client.main.util.Commands.CHAT;
import static src.client.main.util.Commands.FIND_OPPONENT;
import static src.client.main.util.Commands.SET_NAME;


public class CommanderSender {
    private static final Logger logger = LoggerFactory.getLogger(CommanderSender.class);

    private static PrintWriter output;
    private static CommanderSender commanderSender = null;

    private CommanderSender(){}

    public static CommanderSender getInstance(){
        if (commanderSender == null){
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
                output.println(CHAT + value);
                break;
            case SET_NAME:
                output.println(SET_NAME + value);
                logger.info("Sending server cmmand SET_NAME");
                break;
            case CANCEL:
                output.println(CANCEL);
                break;
            default:
                System.out.println("unknown command");
        }
    }

    public void process(Commands command, int value) {
        logger.info("got command here: " + command);
        logger.info("also got message: " + value);
        output.println(command + "" + value);


    }
}
