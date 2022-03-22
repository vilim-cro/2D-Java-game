package com.vilim;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    public Display display;
    private int width;
    private int height;
    private String title;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    //States declaration

    private State gameState;
    private State menuState;
    private State settingsState;
    // Input

    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Camera

    private GameCamera gameCamera;
    //Handler

    private Handler handler;
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.keyManager = new KeyManager();
        this.mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);

        //Enable access to keyboard
        display.getFrame().addKeyListener(keyManager);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        //Handler
        handler = new Handler(this);

        //Enable camera
        gameCamera = new GameCamera(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();
        if (State.getState() != null)
            State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear screen

        g.clearRect(0,0, width, height);

        // Here begins drawing

        if (State.getState() != null) {
            State.getState().render(g);
        }
        g.setColor(Color.BLACK);
        // End drawing here

        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1_000_000_000) {
//                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public State getGameState() {
        return gameState;
    }
}