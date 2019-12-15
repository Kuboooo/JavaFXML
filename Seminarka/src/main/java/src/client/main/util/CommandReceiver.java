package src.client.main.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;
import src.client.main.controller.LoginController;
import src.client.main.controller.LookingForOpponentController;
import src.client.main.controllerInterface.ControllerInterface;


public class CommandReceiver {


    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);

    private static ControllerInterface currentControler;

    public static ControllerInterface getCurrentControler() {
        return currentControler;
    }

    public static void setCurrentControler(ControllerInterface currentControler) {
        CommandReceiver.currentControler = currentControler;
    }

    private CommandReceiver (){}

    public static CommandReceiver commandReceiver = null;
    private GameLayoutController glc;
    private LoginController lc;
    private LookingForOpponentController lfoc;

    public void process(String message){

        Commands commands = findCommand(message);
        logger.info("command received " + commands);

        logger.info("cut message into " + message);
        switch (commands){
            case CANCEL:
                logger.debug("gmlc is: " + getCurrentControler());
                GameLayoutController gmlc = ((GameLayoutController) getCurrentControler());
                logger.debug("gmlc is: " + gmlc);

                gmlc.setUpForPlay();
            case FIND_OPPONENT:
                break;
            case SET_NAME:
                message = cutCommand(message, commands);
                if (message.equals("ok")) {
                    logger.debug("getLC is: " + getCurrentControler());
                    LoginController loginController = ((LoginController) getCurrentControler());
                    logger.debug("getLC is: " + loginController);
                    loginController.loadUpGameLayout();
                }else {
                    getLc().errorLogin();
                }
                break;
            case CHAT:
                message = cutCommand(message, commands);
                GameLayoutController gameLayoutController = ((GameLayoutController) getCurrentControler());
                gameLayoutController.appendMessage(message);
                break;
            default:
                System.out.println("unknown command");
        }
    }

    private Commands findCommand(String input){
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

    private String cutCommand(String combo, Commands commands){

        return combo.substring(commands.getCommand().length());
    }

    public static CommandReceiver getInstance(){
        if (commandReceiver == null) {
            commandReceiver = new CommandReceiver();
        }
        return commandReceiver;
    }

    public void setGlc(GameLayoutController glc) {
        this.glc = glc;
    }

    public void setLc(LoginController lc) {
        this.lc = lc;
    }

    public void setLfoc(LookingForOpponentController lfoc) {
        this.lfoc = lfoc;
    }

    public GameLayoutController getGlc() {
        return glc;
    }

    public LoginController getLc() {
        return lc;
    }

    public LookingForOpponentController getLfoc() {
        return lfoc;
    }
}
