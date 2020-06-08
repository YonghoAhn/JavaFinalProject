package model.ship;

import model.Row;

public class Battleship extends Ship {
    public Battleship() {
        super(4);
    }

    @Override
    public boolean attack(Row row) {
        return false;
    }
}
