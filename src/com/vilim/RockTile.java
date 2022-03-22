package com.vilim;

public class RockTile extends Tiles{

    public RockTile(int id) {
        super(Assets.rock, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
