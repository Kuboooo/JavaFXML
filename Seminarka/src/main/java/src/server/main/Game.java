package src.server.main;

import java.net.Socket;

public class Game {

    private Player playerO;

    private Player playerX;

    public Player getPlayerO() {
        return playerO;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public Socket getOpponentSocket(Socket socket){
        if (socket.equals(playerO.getSocket())){
            return playerX.getSocket();
        }else {
            return playerO.getSocket();
        }
    }
}
