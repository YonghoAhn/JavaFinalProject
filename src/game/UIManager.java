package game;

import model.Player;
import model.Row;
import ui.*;

import java.io.IOException;

public class UIManager{
    private static UIManager instance = null;
    private UIState uiState;
    private Screen currentScreen;

    public void setCurrentScreen(Screen screen)
    {
        currentScreen = screen;
    }

    public static UIManager getInstance()
    {
        if(instance == null)
        {
            instance = new UIManager();
        }
        return instance;
    }

    private UIManager()
    {
        currentScreen = new MainScreen();
    }

    public void clearScreen() throws IOException, InterruptedException {
        //do CLS
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    public String getPlayerMap(int mode, Player player) {
        StringBuilder sb = new StringBuilder();
        int vIdx = 1;
        sb.append("  01 02 03 04 05 06 07 08 09 10\n");
        if(mode==0) {
            for (Row[] rows : player.getPlayerRows()) {
                sb.append(String.format("%02d",vIdx++));
                for (Row row : rows)
                    sb.append(" ").append(row.toString()).append(" ");
                sb.append("\n");
            }
        }
        else
        {
            for (Row[] rows : player.getPlayerRows()) {
                sb.append(String.format("%02d",vIdx++));
                for (Row row : rows)
                    sb.append(" ").append(row.toStringFoe()).append(" ");
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    public void printGame()
    {
        while (!(currentScreen instanceof EndScreen))
            currentScreen.printScreen();
        currentScreen.printScreen();
    }
}
