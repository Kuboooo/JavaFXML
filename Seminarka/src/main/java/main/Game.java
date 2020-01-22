package main;

import java.net.Socket;

public class Game {

    private Player playerO;
    private Player playerX;

    private int playerCount = 0;

    public Socket getOpponentSocket(Socket currentPlayersocket) {
        if (currentPlayersocket.equals(playerO.getSocket())) {
            return playerX.getSocket();
        } else {
            return playerO.getSocket();
        }
    }

    public Player getOpponent(Socket currentPlayersocket) {
        if (currentPlayersocket.equals(playerO.getSocket())) {
            return playerX;
        } else {
            return playerO;
        }
    }

    public void cancelPlayer(Player p) {
        if (p.equals(playerO)) {
            playerO = null;
        } else {
            playerX = null;
        }
        playerCount--;
    }

    public void setPlayer(Player p) {
        if (playerX == null) {
            playerX = p;
            playerX.setToken("X");
        } else {
            playerO = p;
            playerO.setToken("O");
        }
        playerCount++;
    }

    public boolean isFull() {
        return playerCount % 2 == 0;
    }
}
