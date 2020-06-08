package ui;

import game.GameUtil;
import game.Resources;
import model.ship.*;

public class PlaceScreen implements Screen {
    private int batchPoint = 6;

    @Override
    public void printScreen() {
        if (handleInput() == 0)
            if (GAME_INSTANCE.hasNextTurn(0))
                GAME_INSTANCE.getUiManager().setCurrentScreen(new PlaceScreen());
            else
                GAME_INSTANCE.getUiManager().setCurrentScreen(new PlayScreen());
    }

    @Override
    public int handleInput() {
        System.out.println(Resources.placeTooltip);
        while (batchPoint > 0) {
            try {
                GAME_INSTANCE.getUiManager().clearScreen();
            } catch (Exception ignored) {
            }
            System.out.println("Place Screen:");
            System.out.println("Player #" + (GAME_INSTANCE.getCurrentTurnFlag() + 1));
            System.out.println("남은 포인트: " + batchPoint);
            System.out.println(GAME_INSTANCE.getPlayerMap(0));
            System.out.println("배치할 함선 종류와 위치를 입력해 주세요(ex> DD 1 1): ");
            String input = GAME_INSTANCE.getInputManager().readInputString();
            switch (input) {
                case "DD":
                    assignShip(new Destroyer());
                    break;
                case "CA":
                    assignShip(new Cruiser());
                    break;
                case "BB":
                    assignShip(new Battleship());
                    break;
                case "CV":
                    assignShip(new Carrier());
                    break;
                default:
                    System.out.println("다시 입력해 주세요.");
                    break;
            }
        }
        return 0;
    }

    private void assignShip(Ship ship) {
        int row = GAME_INSTANCE.getInputManager().readInputInteger() - 1;
        int column = GAME_INSTANCE.getInputManager().readInputInteger() - 1;
        if (GAME_INSTANCE.setPlayerShip(ship, row, column)) {
            switch (GameUtil.getShipTag(ship)) {
                case "DD":
                    batchPoint -= 1;
                    break;
                case "CA":
                    batchPoint -= 2;
                    break;
                case "BB":
                case "CV":
                    batchPoint -= 3;
                    break;
            }
        }
    }
}
