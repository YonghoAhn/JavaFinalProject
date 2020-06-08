package battleship;

import game.UIManager;

public class battleship {
    public static void main(String[] args){
        /*
        Program enter point
         */
        UIManager uiManager = UIManager.getInstance();
        uiManager.printGame();
    }
}