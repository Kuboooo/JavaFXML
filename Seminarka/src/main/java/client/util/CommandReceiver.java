package client.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.game.Game;
import client.game.Player;
import client.controller.GameLayoutController;
import client.controller.LoginController;
import client.controllerInterface.ControllerInterface;


public class CommandReceiver {
    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);
    private static CommandReceiver commandReceiver = null;
    private static ControllerInterface currentController;

    private CommandReceiver() {
    }

    public static CommandReceiver getInstance() {
        if (commandReceiver == null) {
            commandReceiver = new CommandReceiver();
        }
        return commandReceiver;
    }

    void process(String message) {
        Commands commands = findCommand(message);
        logger.debug("command received " + commands);
        logger.debug("message received " + message);
        switch (commands) {
            case CANCEL:
                break;
            case FIND_OPPONENT:
                message = cutCommand(message, commands);
                Game.getCurrentPlayer().setToken(message.charAt(0));
                if (Game.getCurrentPlayer().getToken() == 'X') {
                    Game.setIsMyTurn(true);
                }
                    Game.setOpponentPlayer(new Player(message.substring(1)));
                getGameLayoutController().setUpForPlay();
                break;
            case SET_NAME:
                message = cutCommand(message, commands);
                if (message.startsWith("ok")) {
                    Game.setCurrentPlayer(new Player(message.substring(2)));
                    getLoginController().loadUpGameLayout();
                } else {
                    getLoginController().errorLogin();
                }
                break;
            case CHAT:
                message = cutCommand(message, commands);
                message = Game.getPlayerFromToken(message.charAt(0)).getName() + ": " + message.substring(1);
                getGameLayoutController().appendMessage(message);
                break;
            case MOVE:
                message = cutCommand(message, commands);
                char charAt0 = message.charAt(0);
                char charAt1 = message.charAt(1);

                if (charAt1 == Game.getCurrentPlayer().getToken()) {
                    getGameLayoutController().presentMove(charAt0, charAt1);
                    Game.getMoveList().add(charAt0);
                    if (Game.whoWin(Game.getMoveList())) {
                        getGameLayoutController().updateIWin();
                    }
                    Game.setIsMyTurn(false);
                } else {
                    getGameLayoutController().presentMove(charAt0, charAt1);
                    Game.getOpponentMoveList().add(charAt0);
                    if (Game.whoWin(Game.getOpponentMoveList())) {
                        getGameLayoutController().updateOpponentWin();
                    }
                    Game.setIsMyTurn(true);
                }
            default:
               logger.error("unknown command");
        }
    }
    /*Returns command from a string containing both command and message*/
    private Commands findCommand(String input) {
        for (Commands command : Commands.values()) {
            if (input.toLowerCase().startsWith(command.getCommand())) {
                logger.debug("Found Command: " + command);
                return command;
            }
        }
        logger.error("No commands, throwing null");
        return null;
    }

    /*Cuts command from message, returns plain message*/
    private String cutCommand(String combo, Commands commands) {
        return combo.substring(commands.getCommand().length());
    }


    private LoginController getLoginController() {
        return (LoginController) currentController;
    }

    private GameLayoutController getGameLayoutController() {
        return (GameLayoutController) currentController;
    }

    public void setCurrentControler(ControllerInterface currentController) {
        CommandReceiver.currentController = currentController;
    }

}
