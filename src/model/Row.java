package model;

import model.ship.Ship;

public class Row {
    private Ship ship;
    private boolean isAttacked;

    public Row() {
        ship = null;
    }

    public void placeShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isPlaceable() {
        return ship == null;
    }

    @Override
    public String toString() {
        if (ship == null) return "□";
        else return "■";
    }

    public String toStringFoe() {
        if (ship == null && isAttacked) return "●";
        if (ship == null) return "□";
        if (ship.isSunk()) return "■";
        if (!ship.isSunk() && isAttacked) return "○";
        else return "□";
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public int attack(int dmg) {
        ship.setHp(dmg);
        isAttacked = true;
        return ship.getHp();
    }

    public void setAttacked(boolean b) {
        isAttacked = b;
    }
}
