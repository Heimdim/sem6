package models;

import java.io.Serializable;

public class Borders implements Serializable
{
    private int lowBorder,highBorder;

    public Borders(int lowBorder, int highBorder)
    {
        this.lowBorder = lowBorder;
        this.highBorder = highBorder;
    }

    public int getLowBorder() {
        return lowBorder;
    }
    public int getHighBorder() {
        return highBorder;
    }

    public String toString()
    {
        return lowBorder+" - "+highBorder;
    }
}