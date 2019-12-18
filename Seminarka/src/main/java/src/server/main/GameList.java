package src.server.main;

import java.util.List;

public class GameList {


    public  static List<Game> gameList;
    public static int gamesRunning;

    public synchronized static List<Game> getGameList() {
        return gameList;
    }

    public synchronized static void setGameList(List<Game> gameList) {
        GameList.gameList = gameList;
    }
}
