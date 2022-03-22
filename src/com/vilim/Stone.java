package com.vilim;

import java.awt.*;

public class Stone extends StaticEntity{

        public Stone(Handler handler, double x, double y) {
            super(handler, x, y, Tiles.TILE_WIDTH, Tiles.TILE_HEIGHT);
            bounds.x = 0;
            bounds.y = 0;
            bounds.width = width;
            bounds.height = height;
        }

        @Override
        public void tick() {

        }

        @Override
        public void render(Graphics g) {
            g.drawImage(Assets.stone,
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }
}
