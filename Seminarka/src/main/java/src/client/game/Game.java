package src.client.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static Player currentPlayer;
    private static Player opponentPlayer;
    private static Game game = null;
    private static  boolean isMyTurn = false;
    private static boolean iWin = false;
    private static boolean opponentWin = false;
    private static Collection<Character> moveList = new ArrayList<>();
    private static Collection<Character> winnerList = new ArrayList<>();
    private static Collection<Character> opponentMoveList = new ArrayList<>();
    private static Collection<Character> row1 = new ArrayList<>(Arrays.asList ('1', '2', '3'));
    private static Collection<Character> row2 = new ArrayList<>(Arrays.asList ('4', '5', '6'));
    private static Collection<Character> row3 = new ArrayList<>(Arrays.asList ('7', '8', '9'));
    private static Collection<Character> col1 = new ArrayList<>(Arrays.asList ('1', '4', '7'));
    private static Collection<Character> col2 = new ArrayList<>(Arrays.asList ('2', '5', '8'));
    private static Collection<Character> col3 = new ArrayList<>(Arrays.asList ('3', '6', '9'));
    private static Collection<Character> dia1 = new ArrayList<>(Arrays.asList ('1', '5', '9'));
    private static Collection<Character> dia2 = new ArrayList<>(Arrays.asList ('7', '5', '3'));

    public synchronized static Collection<Character> getWinnerList() {
        return winnerList;
    }

    public synchronized static void setWinnerList(Collection<Character> winnerList) {
        Game.winnerList = winnerList;
    }

    public synchronized static boolean isOpponentWin() {
        return opponentWin;
    }

    public synchronized static void setOpponentWin(boolean opponentWin) {
        Game.opponentWin = opponentWin;
    }

    public synchronized static Collection<Character> getOpponentMoveList() {
        return opponentMoveList;
    }

    public synchronized static void setOpponentMoveList(Collection<Character> opponentMoveList) {
        Game.opponentMoveList = opponentMoveList;
    }

    public  synchronized static Collection<Character> getMoveList() {
        return moveList;
    }

    public synchronized static void setMoveList(Collection<Character> moveList) {
        Game.moveList = moveList;
    }

    public synchronized static boolean whoWin(Collection<Character> currentOrOpponent){

        List<Character>  helpRowList = new ArrayList<>(row1);
        List<Character>  helpMoveList  = new ArrayList<>(currentOrOpponent);
        logger.debug("help move list starting: " + helpMoveList);


        logger.debug("help move list first before removal: " + helpRowList);
        helpRowList.removeAll(helpMoveList);

        logger.debug("help move list first removal: " + helpRowList);

        if (helpRowList.isEmpty()){
            logger.debug("You win in a line 1");
            iWin = true;
            winnerList = row1;
            return true;
        }
        helpRowList = new ArrayList<>(row2);
        helpRowList.removeAll(helpMoveList);

        logger.debug("help move list second removal: " + helpRowList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a line 2");
            iWin = true;

            winnerList = row2;
            return true;
        }
        helpRowList = new ArrayList<>(row3);

        logger.debug("help move list third before removal: " + helpRowList);
        helpRowList.removeAll(helpMoveList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a line 3");
            iWin = true;
            winnerList = row3;
            return true;
        }
        helpRowList = new ArrayList<>(col1);
        helpRowList.removeAll(helpMoveList);

        logger.debug("help move list col1 removal: " + helpRowList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a column 1");
            iWin = true;
            winnerList = col1;
            return true;
        }
        helpRowList = new ArrayList<>(col2);
        helpRowList.removeAll(helpMoveList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a col 2");
            iWin = true;
            winnerList = col2;
            return true;
        }
        helpRowList = new ArrayList<>(col3);
        helpRowList.removeAll(helpMoveList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a col 3");
            iWin = true;
            winnerList = col3;
            return true;
        }
        helpRowList = new ArrayList<>(dia1);
        helpRowList.removeAll(helpMoveList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a dia 1");
            iWin = true;
            winnerList = dia1;
            return true;
        }
        helpRowList = new ArrayList<>(dia2);
        helpRowList.removeAll(helpMoveList);
        if (helpRowList.isEmpty()){
            logger.debug("You win in a dia 2");
            iWin = true;
            winnerList = dia2;
            return true;
        }

        return false;
    }


    public synchronized static boolean isiWin() {
        return iWin;
    }

    public synchronized static void setiWin(boolean iWin) {
        Game.iWin = iWin;
    }

    public synchronized static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    public static Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public synchronized static void setOpponentPlayer(Player opponentPlayer) {
        Game.opponentPlayer = opponentPlayer;
    }


    public synchronized static boolean isIsMyTurn() {
        return isMyTurn;
    }

    public synchronized static void setIsMyTurn(boolean isMyTurn) {
        Game.isMyTurn = isMyTurn;
    }

    private Game(){

    }

    public synchronized static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return game;
    }

}
