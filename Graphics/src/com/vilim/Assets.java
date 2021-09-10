package com.vilim;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 62;
    private static final int height = 62;
    public static BufferedImage rock, sand, grass, dirt, bricks, stone, inventory;
//    public static BufferedImage[] trees;
    public static BufferedImage start, quit;
    public static BufferedImage tree;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;

    public static void init() {
        SpriteSheet tilesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/picture4.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/picture12.png"));
        SpriteSheet entitySheet = new SpriteSheet(ImageLoader.loadImage("/textures/picture14.png"));
        SpriteSheet iconSheet = new SpriteSheet((ImageLoader.loadImage("/textures/picture16.png")));

        rock = tilesSheet.crop(0, 0, width, height);
        sand = tilesSheet.crop(0, height, width, height);
        grass = tilesSheet.crop(0, 2 * height, width, height);
        dirt = tilesSheet.crop(0, 3 * height, width, height);
        bricks = tilesSheet.crop(0, 4 * height, width, height);

        stone = entitySheet.crop(460, 460, 40, 30);

        tree = entitySheet.crop(60, 115, 60, 70);
//        trees = new BufferedImage[5];
//
//        for(int i = 0; i < 5; i++)
//            trees[i] = entitySheet.crop(61 + 100 * i,115, 60, 70);

        start = iconSheet.crop(400, 680, 850, 350);
        quit = iconSheet.crop(400, 350, 850, 330);

        inventory = ImageLoader.loadImage("/textures/picture20.png");

        playerDown = new BufferedImage[4];
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerRight = new BufferedImage[4];

        for(int i = 0; i < 4; i++)
            playerDown[i] = playerSheet.crop(400 * i, 0, 400, 600);
        for(int i = 0; i < 4; i++)
            playerUp[i] = playerSheet.crop(400 * i, 600, 400, 600);
        for(int i = 0; i < 4; i++)
            playerLeft[i] = playerSheet.crop(400 * i, 1200, 400, 600);
        for(int i = 0; i < 4; i++)
            playerRight[i] = playerSheet.crop(400 * i, 1800, 400, 597);
    }
}