package client.main.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.game.Game;
import client.game.Player;
import client.main.controller.GameLayoutController;
import client.main.controller.LoginController;
import client.main.controllerInterface.ControllerInterface;


public class CommandReceiver {
    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);
    private static CommandReceiver commandReceiver = null;
    private static ControllerInterface currentControler;

    private CommandReceiver() {
    }

    private ControllerInterface getCurrentControler() {
        return currentControler;
    }

    public void setCurrentControler(ControllerInterface currentControler) {
        CommandReceiver.currentControler = currentControler;
    }

    public static CommandReceiver getInstance() {
        if (commandReceiver == null) {
            commandReceiver = new CommandReceiver();
        }
        return commandReceiver;
    }

    public void process(String message) {

        Commands commands = findCommand(message);
        logger.info("command received " + commands);
        GameLayoutController glc;
        logger.info("cut message into " + message);
        switch (commands) {
            case CANCEL:
                break;
            case FIND_OPPONENT:
                message = cutCommand(message, commands);
                logger.debug("Opponent found ");
                glc = ((GameLayoutController) getCurrentControler());
                logger.debug("gmlc is: " + glc);
                glc.setUpForPlay();
                logger.debug("gmlc is: " + glc);
                Game.getCurrentPlayer().setToken(message.charAt(0));
                if (Game.getCurrentPlayer().getToken() == 'X') {
                    Game.setIsMyTurn(true);
                }
                logger.debug(Game.getCurrentPlayer().getToken() + " Opopnent token, and is my turn?" + Game.isIsMyTurn());
                break;
            case SET_NAME:
                message = cutCommand(message, commands);
                logger.info(message);
                logger.debug("yay or nay" + message.startsWith("ok"));
                if (message.startsWith("ok")) {
                    LoginController loginController = ((LoginController) getCurrentControler());
                    Game.setCurrentPlayer(new Player(message.substring(2)));
                    logger.debug("getLC is: " + loginController);
                    loginController.loadUpGameLayout();
                } else {
                    LoginController loginController = ((LoginController) getCurrentControler());
                    loginController.errorLogin();

                }
                break;
            case CHAT:
                message = cutCommand(message, commands);
                glc = ((GameLayoutController) getCurrentControler());
                glc.appendMessage(message);
                break;
            case MOVE:
                message = cutCommand(message, commands);
                glc = ((GameLayoutController) getCurrentControler());

                if (message.charAt(1) == Game.getCurrentPlayer().getToken()) {
                    glc.dab(message.charAt(0), message.charAt(1));
                    logger.debug("added to me " + message.charAt(0));
                    Game.getMoveList().add(message.charAt(0));
                    if (Game.whoWin(Game.getMoveList())) {
                        logger.debug("We  won, updating");
                        Game.setWinnerList(Game.getWinnerList());
                        glc.updateIWin();
                    }
                    Game.setIsMyTurn(false);
                } else {
                    glc.dab(message.charAt(0), message.charAt(1));
                    Game.getOpponentMoveList().add(message.charAt(0));
                    logger.debug("added to opponent " + message.charAt(0));
                    if (Game.whoWin(Game.getOpponentMoveList())) {
                        logger.debug("Opponent won, updating");
                        Game.setWinnerList(Game.getWinnerList());
                        glc.updateOpponentWin();
                    }
                    Game.setIsMyTurn(true);
                }
            default:
                System.out.println("unknown command");
        }
    }

    private Commands findCommand(String input) {
        logger.info("Going through commands input: " + input);
        for (Commands s : Commands.values()) {
            logger.debug("Command: " + s.getCommand());
            if (input.toLowerCase().startsWith(s.getCommand())) {
                logger.debug("Found Command that fits: lower" + s);
                return s;
            }
        }
        logger.debug("No commands, throwing null");
        return null;
    }

    private String cutCommand(String combo, Commands commands) {

        return combo.substring(commands.getCommand().length());
    }
}
