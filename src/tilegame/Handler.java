package tilegame;

import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.worlds.World;

public class Handler
{
    public Game game;
    public World world;

    public Handler(Game game)
    {
        this.game = game;
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }
}
