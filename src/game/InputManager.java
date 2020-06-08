package game;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner;
    private static InputManager instance = null;

    public InputManager()
    {
        scanner = new Scanner(System.in);
    }

    public static InputManager getInstance()
    {
        if(instance == null)
        {
            instance = new InputManager();
        }
        return instance;
    }

    public char readInputCharacter() {
        return scanner.next().charAt(0);
    }

    public int readInputInteger() {
        return scanner.nextInt();
    }

    public String readInputString() {
        return scanner.next();
    }
}
