package model.ship;

import game.Resources;

public abstract class Ship implements Attackable {
    private int hp;

    public Ship(int hp)
    {
        this.hp = hp;
    }

    private boolean isSunk = false;
    /**
     * Returns the HP of this class
     * @return hp: Health point of this class
     */
    public int getHp() {
        return hp;
    }

    /**
     * Decreases the HP of this class
     * @param amount decrease HP point(minus amount will increase HP)
     */
    public void setHp(int amount)
    {
        hp -= Math.min(amount,hp);
        if(hp==0) {
            System.out.println(Resources.explosion);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            isSunk = true;
        }
    }

    /**
     * Returns the model.ship has sunk
     * @return is model.ship sink?
     */
    public boolean isSunk() {
        return isSunk;
    }
}
