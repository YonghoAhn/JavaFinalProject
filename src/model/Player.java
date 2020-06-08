package model;

import game.GameUtil;
import model.ship.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Row[][] playerRows = new Row[10][10];
    private final List<Ship> ships = new ArrayList<>();

    public Player() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                playerRows[i][j] = new Row();
    }

    public List<Ship> getShips()
    {
        return ships;
    }

    public boolean addShip(Ship ship, int row, int column) {
        if (!playerRows[row][column].isPlaceable()) return false;
        playerRows[row][column].placeShip(ship);
        ships.add(ship);
        return true;
    }

    public boolean isPlayerLose() {
        for (Ship s : ships)
            if (!s.isSunk()) return false;
        return true;
    }

    public boolean isPlayerHasShip(String tag)
    {
        for (Ship s : ships)
            if(GameUtil.getShipTag(s).equals(tag) && !s.isSunk()) return true;
        return false;
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
        playerRows[row][column].setAttacked(true);
        //return hp of ship
        if(!playerRows[row][column].isPlaceable()) {
            if (!playerRows[row][column].getShip().isSunk())
                return playerRows[row][column].attack(dmg);
            else return -100;
        }
        return -200;
    }

    public Row[][] getPlayerRows()
    {
        return playerRows;
    }
}
