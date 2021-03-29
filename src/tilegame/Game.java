package tilegame;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable
{
    public String title;
    public State gameState;
    public State menuState;
    private int width, height;
    private Display display;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private boolean running = false;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private GameCamera gameCamera;
    private Handler handler;

    public Game(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    @Override
    public void run()
    {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
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

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    private void init()
    {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0, 0);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.currentState = menuState;
    }

    private void tick()
    {
        keyManager.tick();

        if (State.currentState != null)
            State.currentState.tick();
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear screen
        g.fillRect(0, 0, width, height);
        // Draw
        if (State.currentState != null)
            State.currentState.render(g);
        // End drawing
        bs.show();
        g.dispose();
    }

    public synchronized void start()
    {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running)
            return;

        running = false;

        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager()
    {
        return mouseManager;
    }

    public GameCamera getGameCamera()
    {
        return gameCamera;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
