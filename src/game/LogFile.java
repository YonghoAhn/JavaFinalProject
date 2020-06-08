package game;

import sun.rmi.runtime.Log;

import java.io.*;
import java.text.SimpleDateFormat;

public class LogFile {
    private int turn;
    private String tag;
    private String tag2;
    private int dmg;
    private int row;
    private int column;
    private final String filename;

    public LogFile()
    {
        long time = System.currentTimeMillis(); System.out.println(time);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy년 MM월 dd일 aa hh시 mm분 ss초의 게임");
        filename = simple.format(time);
    }

    public void setData(int turn, String tag, String tag2, int dmg, int row, int column)
    {
        this.turn = turn;
        this.tag = tag;
        this.dmg = dmg;
        this.row = row;
        this.column = column;
        this.tag2 = tag2;
    }

    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
        String output = "=turn " + this.turn + "=" + "\n"
                + this.tag + " " + this.tag2 + " " + this.row + " " + this.column + "  " + this.dmg + "\n";
        fileOutputStream.write(output.getBytes());
        fileOutputStream.close();
    }
}