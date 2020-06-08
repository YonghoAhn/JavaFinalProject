package game;

import model.ship.*;

public class GameUtil {
    public static String getShipTag(Ship ship)
    {
        if(ship instanceof Destroyer) return "DD";
        else if(ship instanceof Cruiser) return "CA";
        else if (ship instanceof Carrier) return "CV";
        else if (ship instanceof Battleship) return "BB";
        else return "ER";
    }
    public static int getShipDmg(String tag)
    {
        switch (tag) {
            case "DD":
                return 1;
            case "CA":
                return 2;
            case "CV":
                return 3;
            case "BB":
                return 4;
            default:
                return 0;
        }
    }
}
