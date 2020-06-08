package ui;

import game.Resources;

public class MainScreen implements Screen {
    @Override
    public void printScreen()
    {
        try {
            GAME_INSTANCE.getUiManager().clearScreen();
        } catch (Exception ignored) {
        }
        printLogo();
        System.out.println("1. 핫시트 플레이");
        System.out.println("2. 튜토리얼");
        int input = handleInput();
        //change screen
        if(input == 1)
            GAME_INSTANCE.getUiManager().setCurrentScreen(new PlaceScreen());
        else if(input == 2)
            GAME_INSTANCE.getUiManager().setCurrentScreen(new TutorialScreen());
    }

    @Override
    public int handleInput() {
        while(true)
        {
            char ch = GAME_INSTANCE.getInputManager().readInputCharacter();
            if(ch == 'e') return -1;
            if(ch == '1') return 1;
            if(ch == '2') return 2;
        }
    }

    public static void printLogo()
    {
        System.out.println(Resources.logo);
    }
}
