package com.vilim;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"C:\\Users\\Vilim\\IdeaProjects\\Graphics\\src\\com\\vilim\\res\\worlds\\world1.txt");
        handler.setWorld(world);
        player = handler.getWorld().getEntityManager().getPlayer();
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.drawAttacked(g);
    }
}
