package com.vilim;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 128;
    public static final int DEFAULT_CREATURE_HEIGHT = 128;

    protected float speed;

    protected float xMove, yMove;

    public Creature(Handler handler, double x, double y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if(!checkEntityCollision(xMove, 0f))
            moveX();
        if(!checkEntityCollision(0f, yMove))
            moveY();
    }

    public void moveX() {
        if(xMove > 0) { //moving right
            int tileX = (int)(x + xMove + bounds.x + bounds.width) / Tiles.TILE_WIDTH;

            if(!collisionWithTile(tileX, (int)(y + bounds.y) / Tiles.TILE_HEIGHT) &&
                    !collisionWithTile(tileX, (int)(y + bounds.y + bounds.height) / Tiles.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tileX * Tiles.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }

        else if(xMove < 0) { //moving left
            int tileX = (int)(x + xMove + bounds.x) / Tiles.TILE_WIDTH;
            if(!collisionWithTile(tileX, (int)(y + bounds.y) / Tiles.TILE_HEIGHT) &&
                    !collisionWithTile(tileX, (int)(y + bounds.y + bounds.height) / Tiles.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tileX * Tiles.TILE_WIDTH + Tiles.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if(yMove < 0) { //up
            int tileY = (int) (y + yMove + bounds.y) / Tiles.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tiles.TILE_WIDTH, tileY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tiles.TILE_WIDTH, tileY)) {
                y += yMove;
            } else {
                y = tileY * Tiles.TILE_HEIGHT + Tiles.TILE_HEIGHT - bounds.y;
            }
        }

        else if(yMove > 0) { //down
            int tileY = (int) (y + yMove + bounds.y + bounds.height) / Tiles.TILE_HEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tiles.TILE_WIDTH, tileY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tiles.TILE_WIDTH, tileY)) {
                y += yMove;
            } else {
                y = tileY * Tiles.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //Getters and Setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
