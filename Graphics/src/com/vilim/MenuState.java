package com.vilim;

import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 150, 750, 250,
                Assets.start.getScaledInstance(750, 250, Image.SCALE_SMOOTH), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().getGameState());
            }
        }));
        uiManager.addObject(new UIImageButton(200, 450, 750, 250,
                Assets.quit.getScaledInstance(750, 250, Image.SCALE_SMOOTH), new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 ,1200, 800);
        uiManager.render(g);
    }
}
