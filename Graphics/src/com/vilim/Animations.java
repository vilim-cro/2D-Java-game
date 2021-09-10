package com.vilim;

import java.awt.image.BufferedImage;

public class Animations {
    private float timer;
    private int index;
    private BufferedImage[] images;
    private long lastTime;
    private int speed;

    public Animations(BufferedImage[] images, int speed) {
        this.images = images;
        this.timer = 0;
        this.index = 0;
        this.speed = speed;
        this.lastTime = System.currentTimeMillis();
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer >= speed) {
            index++;
            timer = 0;
            if(index >= images.length)
                index = 0;
        }
    }

    public BufferedImage getImage() {
        return images[index];
    }
}
