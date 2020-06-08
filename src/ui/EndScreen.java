package ui;

import game.Resources;
import sun.applet.Main;

import java.util.Scanner;

public class EndScreen implements Screen{
    @Override
    public void printScreen() {
        try {
            GAME_INSTANCE.getUiManager().clearScreen();
        } catch (Exception ignored) {}
        System.out.println("END SCREEN");
        int result = GAME_INSTANCE.isGameEnd();
        if(result == -1)
        {
            //player 0 win
            System.out.println(Resources.player1Win);
        }
        else
        {
            //player 1 win
            System.out.println(Resources.player2Win);
        }
        handleInput();
    }

    @Override
    public int handleInput() {
        while (true)
        {
            System.out.println("end를 입력하면 게임을 끝냅니다.");
            System.out.println("restart를 입력하면 게임을 다시 시작합니다.");
            String input = GAME_INSTANCE.getInputManager().readInputString();
            if(input.equals("end")) break;
            else if(input.equals("restart"))
            {
                GAME_INSTANCE.initialize();
                GAME_INSTANCE.getUiManager().setCurrentScreen(new MainScreen());
                break;
            }
        }
        return 0;
    }
}
