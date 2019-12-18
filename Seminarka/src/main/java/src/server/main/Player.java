package src.server.main;

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

    public void setToken(String token) {
        this.token = token;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
