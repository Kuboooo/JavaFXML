package src.client.main.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;
import src.client.main.controller.LoginController;
import src.client.main.controller.LookingForOpponentController;


public class CommandReceiver {


    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);


    private CommandReceiver (){}

    public static CommandReceiver commandReceiver = null;
    private GameLayoutController glc;
    private LoginController lc;
    private LookingForOpponentController lfoc;

    public void process(String message){

        Commands commands = findCommand(message);
        logger.info("command received " + commands);
        message = cutCommand(message, commands);
        logger.info("cut message into " + message);
        switch (commands){
            case PLAY:
                break;
            case SET_NAME:
                if (message.equals("ok")) {
                    logger.debug("getLC is: " + getLc());
                    getLc().loadUpGameLayout();
                }else {
                    getLc().errorLogin();
                }
                break;
            case CHAT:
                getGlc().appendMessage(message);
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

    public static CommandReceiver getInstance(GameLayoutController glc, LoginController lc, LookingForOpponentController lfoc){
        if (commandReceiver == null) {
            commandReceiver = new CommandReceiver();
            commandReceiver.setGlc(glc);
            commandReceiver.setLc(lc);
            commandReceiver.setLfoc(lfoc);
            System.out.println(glc);
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
