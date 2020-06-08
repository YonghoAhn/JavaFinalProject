package game;

import model.Player;
import model.Row;
import model.ship.Ship;

public class GameInstance {
    private static GameInstance instance = null;
    private final Player[] players = new Player[2];
    private int currentTurnFlag = 0;
    private int playTurnCounter = 1;
    private final UIManager uiManager = UIManager.getInstance();
    private final InputManager inputManager = InputManager.getInstance();

    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }

    public int getCurrentTurnCounter() {
        return playTurnCounter;
    }

    public void initialize() {
        players[0] = new Player();
        players[1] = new Player();
        currentTurnFlag = 0;
        playTurnCounter = 1;
    }

    public Player getPlayer()
    {
        return players[currentTurnFlag];
    }

    public Player getPlayer(int pos)
    {
        return players[pos];
    }

    public int getCurrentTurnFlag() {
        return currentTurnFlag;
    }

    public boolean hasNextTurn(int mode) {
        if (mode == 0)
            if (currentTurnFlag == 0) {
                currentTurnFlag = 1;
                return true;
            } else {
                currentTurnFlag = 0;
                return false;
            }
        else
        {
            if(isGameEnd()!=0)
                return false;
            else
            {
                if(currentTurnFlag==0) currentTurnFlag = 1;
                else {
                    currentTurnFlag = 0;
                    playTurnCounter++;
                }
                return true;
            }
        }
    }

    /**
     * returns which player win
     * @return return 1 when player 1 wins, return -1 when player 0 wins. if outcome not decided yet, return 0
     */
    public int isGameEnd()
    {
        if(players[0].isPlayerLose())
            return 1;
        if(players[1].isPlayerLose())
            return -1;
        return 0;
    }

    /**
     * attach to specific row
     * @param dmg amount of attack damage
     * @param row target row
     * @param column target column
     * @return if successfully attacked, return hp of ship. if ship already sunk, return -100. if row/column is null, return -200
     */
    public int attack(int dmg, int row, int column)
    {
        int result = players[currentTurnFlag ^ 1].attack(dmg, row, column);
        if(result == 0) try {
            uiManager.clearScreen();
        } catch (Exception ignored) {}
        return result;
    }

    private GameInstance() {
        initialize();
    }

    public boolean setPlayerShip(Ship ship, int row, int column) {
        return players[currentTurnFlag].addShip(ship,row,column);
    }

    public String getPlayerMap(int mode) {
        return uiManager.getPlayerMap(mode, players[currentTurnFlag]);
    }

    public String getPlayerFoeMap() {
        return uiManager.getPlayerMap(1, players[currentTurnFlag^1]);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
