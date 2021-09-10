package com.vilim;

import java.awt.*;

public class Text {

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c) {
        g.setColor(c);
        int x = xPos;
        int y = yPos;
        if(center) {
            FontMetrics fm = g.getFontMetrics(g.getFont());
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2)  + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
