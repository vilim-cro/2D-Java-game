package com.vilim;


public class BrickTile extends Tiles{
    public BrickTile(int id) {
        super(Assets.bricks, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
