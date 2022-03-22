package com.vilim;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tiles {
    //Static
    public static Tiles[] tiles = new Tiles[256];
    public static Tiles rockTile = new RockTile(0);
    public static Tiles sandTile = new SandTile(1);
    public static Tiles grassTile = new GrassTile(2);
    public static Tiles dirtTile = new DirtTile(3);
    public static Tiles brickTile = new BrickTile(4);

    //Class things
    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tiles(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public int getId() {
        return id;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT,null);
    }

    public boolean isSolid() {
        return false;
    }
}
