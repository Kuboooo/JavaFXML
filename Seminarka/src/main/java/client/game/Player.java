package client.game;

public class Player {

    private String name;
    private char token;

    public Player(String name) {
        this.name = name;
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

}
