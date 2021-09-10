package com.vilim;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private Handler handler;

    private boolean active = false;

    private ArrayList<Item> inventoryItems;
    private int invX = 100, invY = 300, invWidth = 1000, invHeight = 300;
    private int invListCenterX = invX + 880;
    private int invListCenterY = invY + 100;
    private String currentDescription = "No item selected";
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

//        addItem(Item.rockItem.createNew(5));
//        addItem(Item.woodItem.createNew(5));
    }

    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        if(!active)
            return;
    }

    public void render(Graphics g) {
        if(!active)
            return;
        g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);

        Iterator<Item> it = inventoryItems.iterator();
        int i = 0;
        int j = 0;
        while (it.hasNext()) {
            Item item = it.next();
            item.render(g, 372 + 49 * j, 340 + 62 * i);
            Text.drawString(g, Integer.toString(item.getCount()), 390 + 49 * j,
                    380 + 62 * i, true, Color.WHITE);
            if(j != 9)
                j++;
            else {
                i++;
                j = 0;
            }
        }

        Text.drawString(g, "Item description:", invListCenterX,
                invListCenterY, true, Color.WHITE);
        Text.drawString(g, currentDescription, invListCenterX,
                invListCenterY + 50, true, Color.WHITE);
    }

    public void addItem(Item item) {
        for(Item i: inventoryItems) {
            if(i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
