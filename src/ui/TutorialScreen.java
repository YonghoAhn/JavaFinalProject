package ui;

import game.Resources;

public class TutorialScreen implements Screen {
    @Override
    public void printScreen() {
        try {
            GAME_INSTANCE.getUiManager().clearScreen();
        } catch (Exception ignored) {
        }
        System.out.println(Resources.tutorialRuleTooltip);
        System.out.println();
        System.out.println(Resources.tutorialShipsTooltip);
        System.out.println("아무 키나 입력하여 메인화면으로 돌아가세요.");
        handleInput();
    }

    @Override
    public int handleInput() {
        GAME_INSTANCE.getInputManager().readInputString();
        GAME_INSTANCE.getUiManager().setCurrentScreen(new MainScreen());
        return 0;
    }
}
