package com.vilim;

public class Handler {
    private final Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
}
