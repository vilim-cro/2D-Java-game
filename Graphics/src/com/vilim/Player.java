package com.vilim;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animations animationDown, animationUp, animationLeft, animationRight;

    //attack
    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
    private boolean isAttacked = false;
    private int xKey, yKey;
    private BufferedImage attackPicture = ImageLoader.loadImage("/textures/picture18.png");

    //inventory

    private Inventory inventory;
    public Player(Handler handler, double x, double y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 55;
        bounds.y = 55;
        bounds.width = 25;
        bounds.height = 50;

        //Animations
        animationDown = new Animations(Assets.playerDown, 500);
        animationUp = new Animations(Assets.playerUp, 500);
        animationLeft = new Animations(Assets.playerLeft, 500);
        animationRight = new Animations(Assets.playerRight, 500);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //attacks
        checkAttacks();

        inventory.tick();
    }

    private void checkAttacks() {
        if(inventory.isActive())
            return;

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            yKey = -1;
        } else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
            yKey = 1;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            xKey = -1;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            xKey = 1;
        } else
            return;

        attackTimer = 0;

        for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                isAttacked = true;
                return;
            }
            isAttacked = false;
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(inventory.isActive())
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(getImage(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    public void postRender(Graphics g) {
        inventory.render(g);
    }

    @Override
    public void die() {
        System.out.println("You're dead");
    }

    private BufferedImage getImage() {
        if(xMove > 0) {
            return animationRight.getImage();
        } else if(xMove < 0) {
            return animationLeft.getImage();
        } else if(yMove > 0) {
            return animationDown.getImage();
        } else
            return animationUp.getImage();
    }

    public void drawAttacked(Graphics g) {
        if(isAttacked) {
            if(xKey != 0) {
                g.drawImage(attackPicture,
                        (int) (this.x + this.bounds.x / 2 - handler.getGameCamera().getxOffset() + 48 * xKey),
                        (int) (this.y) + this.bounds.y / 2 - (int) (handler.getGameCamera().getyOffset()),
                        64, 64, null);
            } else if(yKey != 0)
                g.drawImage(attackPicture,
                        (int) (this.x + this.bounds.x / 2 - handler.getGameCamera().getxOffset()),
                        (int) (this.y + this.bounds.y / 2 - handler.getGameCamera().getyOffset() + 48 * yKey),
                        64, 64, null);
        }
        xKey = 0;
        yKey = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }
}