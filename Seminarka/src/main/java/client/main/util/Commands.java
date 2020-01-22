package client.main.util;

public enum Commands {

    QUIT("quit"),
    LOGIN("login"),
    FIND_OPPONENT("find_opponent"),
    SET_NAME("set_name"),
    CHAT("chat"),
    MOVE("move"),
    CANCEL("cancel");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
