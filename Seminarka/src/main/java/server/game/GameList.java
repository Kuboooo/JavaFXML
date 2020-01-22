package server.game;

import java.util.List;

public class GameList {


    private static List<Game> gameList;

    public synchronized static List<Game> getGameList() {
        return gameList;
    }

    public synchronized static void setGameList(List<Game> gameList) {
        GameList.gameList = gameList;
    }
}
