package com.vilim;

import java.awt.*;

public class Tree extends StaticEntity{

//    private int index = 0;

    public Tree(Handler handler, double x, double y) {
        super(handler, x, y, Tiles.TILE_WIDTH * 2, Tiles.TILE_HEIGHT * 2);
//        index = (int) (Math.random() * 5);
//        System.out.println(Integer.toString(0));
        bounds.x = width / 4;
        bounds.y = height / 2;
        bounds.width = width / 2;
        bounds.height = height / 2;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }
}
