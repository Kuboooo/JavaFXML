package main;

import java.util.List;

public class GameList {


    public static List<Game> gameList;

    public synchronized static List<Game> getGameList() {
        return gameList;
    }

    public synchronized static void setGameList(List<Game> gameList) {
        GameList.gameList = gameList;
    }
}
