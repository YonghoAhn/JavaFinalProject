package model.ship;

import model.Row;

public class Cruiser extends Ship {
    public Cruiser() {
        super(2);
    }

    @Override
    public boolean attack(Row row) {
        return false;
    }
}
