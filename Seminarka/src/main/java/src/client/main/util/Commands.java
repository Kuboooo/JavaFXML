package src.client.main.util;

public enum Commands {

    QUIT("quit"),
    LOGIN("login"),
    PLAY("play"),
    SET_NAME("set_name"),
    CHAT("chat"),
    CANCEL("cancel");

    private final String command;

    Commands(String command){
        this.command = command;
    }

    public String getCommand(){return command;}

}
