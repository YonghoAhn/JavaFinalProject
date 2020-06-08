package model.ship;

import model.Row;

public class Destroyer extends Ship {
    public Destroyer() {
        super(1);
    }

    @Override
    public boolean attack(Row row) {
        return true;
    }
}
