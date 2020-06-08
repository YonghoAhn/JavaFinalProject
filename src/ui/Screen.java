package ui;

import game.GameInstance;
import game.UIManager;

public interface Screen {
    GameInstance GAME_INSTANCE = GameInstance.getInstance();

    void printScreen();
    int handleInput();
}
