package ui;

import game.GameUtil;
import game.LogFile;
import model.Player;
import model.ship.Ship;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.List;

public class PlayScreen implements Screen {
    private LogFile logFile = new LogFile();

    @Override
    public void printScreen() {
        handleInput();
        GAME_INSTANCE.getUiManager().setCurrentScreen(new EndScreen());
    }

    @Override
    public int handleInput() {
        System.out.println("Play Screen:");
        boolean cvTurnCounter = false;
        do {
            try {
                GAME_INSTANCE.getUiManager().clearScreen();
            } catch (Exception ignored) {
            }
            System.out.println("Player #" + (GAME_INSTANCE.getCurrentTurnFlag() + 1));
            System.out.println(GAME_INSTANCE.getPlayerFoeMap());
            Player player = GAME_INSTANCE.getPlayer();
            List<Ship> ships = player.getShips();
            for(Ship s: ships)
            {
                System.out.println(GameUtil.getShipTag(s) + " HP: " + s.getHp());
            }
            while (true) {
                System.out.println("적을 공격할 아군 함선 종류를 입력하세요(ex> DD): ");
                String input = GAME_INSTANCE.getInputManager().readInputString();
                //check if ship exists
                if (!player.isPlayerHasShip(input)) {
                    System.out.println("해당 함종이 침몰했거나 없습니다.");
                    continue;
                }
                System.out.println("공격할 적군 타일을 선택하세요(ex> 1 1):");
                int row = GAME_INSTANCE.getInputManager().readInputInteger() - 1;
                int column = GAME_INSTANCE.getInputManager().readInputInteger() - 1;
                if (row > 9 || column > 9 || row < 0 || column < 0) {
                    System.out.println("인덱스는 1~10 범위여야 합니다.");
                    continue;
                }
                //check if row already attacked
                //if not, attack row and check the ship has sunk
                int attackResult = GAME_INSTANCE.attack(GameUtil.getShipDmg(input), row, column);
                if (attackResult == -100) {
                    System.out.println("해당 칸의 함선은 이미 침몰했습니다.");
                    continue;
                }
                if (attackResult == -200)
                    System.out.println("해당 칸에는 배가 없습니다.");

                if (input.equals("CV") && !cvTurnCounter) {
                    GAME_INSTANCE.hasNextTurn(0);
                    cvTurnCounter = true;
                } else
                    cvTurnCounter = false;

                String tag2;
                Player p = GAME_INSTANCE.getPlayer(GAME_INSTANCE.getCurrentTurnFlag() ^ 1);
                Ship s = p.getPlayerRows()[row][column].getShip();
                if(s == null) tag2 = "NONE";
                else tag2 = GameUtil.getShipTag(s);
                logFile.setData(GAME_INSTANCE.getCurrentTurnCounter(),input,tag2,GameUtil.getShipDmg(input),row,column);
                try
                {
                    logFile.save();
                } catch (IOException ignored) {}
                break;
            }
        } while (GAME_INSTANCE.hasNextTurn(1));

        return 0;
    }
}
