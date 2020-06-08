package model.ship;

import model.Row;

public class Carrier extends Ship {
    public Carrier() {
        super(3);
    }

    @Override
    public boolean attack(Row row) {
        return false;
    }
}
