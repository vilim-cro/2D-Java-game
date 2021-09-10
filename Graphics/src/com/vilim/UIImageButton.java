package com.vilim;

import java.awt.*;

public class UIImageButton extends UIObject{

    private Image image;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height,
                         Image image, ClickListener  clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.image = image;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
