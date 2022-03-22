package com.vilim;

import java.awt.*;

public class World {

    private int width, height;
    private int spawnX, spawnY;
    private int[][] map;
    private Handler handler;
    private int numberOfTrees = 10;
    private int numberOfStones = 5;

    //Entities
    private EntityManager entityManager;

    //Item
    private ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        itemManager = new ItemManager(handler);

        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        loadWorld(path);

        while(numberOfTrees > 0) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map.length);
            if(map[x][y] == 2 && map[x + 1][y] == 2 && map[x][y + 1] == 2 && map[x + 1][y + 1] == 2) {
                entityManager.addEntity(new Tree(handler, x * 64, y * 64));
                numberOfTrees--;
            }
        }

        while(numberOfStones > 0) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map.length);
            if(map[x][y] == 2 && map[x + 1][y] == 2 && map[x][y + 1] == 2 && map[x + 1][y + 1] == 2) {
                entityManager.addEntity(new Stone(handler, x * 64, y * 64));
                numberOfStones--;
            }
        }

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    private void loadWorld (String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        map = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                map[x][y] = Utils.parseInt(tokens[x + y * width + 4]);
            }
        }
    }

    public void tick() {
        entityManager.tick();
        itemManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int)(Math.max(0, handler.getGameCamera().getxOffset() / Tiles.TILE_WIDTH));
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tiles.TILE_WIDTH + 1);
        int yStart = (int)(Math.max(0, handler.getGameCamera().getyOffset() / Tiles.TILE_HEIGHT));
        int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tiles.TILE_HEIGHT + 1);

        for(int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++)
                getTile(x, y).render(g,
                        (int)(x * Tiles.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tiles.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
        }
        itemManager.render(g);
        entityManager.render(g);
    }

    public Tiles getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tiles.grassTile;
        Tiles t = Tiles.tiles[map[x][y]];
        if (t == null)
            return Tiles.grassTile;
        return t;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
