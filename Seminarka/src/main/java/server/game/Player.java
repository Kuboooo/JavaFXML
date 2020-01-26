package server.game;

import java.net.Socket;

public class Player {

    private String playerName;
    private String token;
    private Socket socket;

    public Player(Socket socket) {
        this.socket = socket;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

    Socket getSocket() {
        return socket;
    }

}
