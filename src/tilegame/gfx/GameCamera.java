package tilegame.gfx;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public class GameCamera
{
    private Handler handler;
    public float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace()
    {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.world.getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = handler.world.getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        }
        else if (yOffset > handler.world.getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
            yOffset = handler.world.getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }
    }

    public void centerOnEntity(Entity e)
    {
        xOffset = e.x - handler.getWidth() / 2 + e.width / 2;
        yOffset = e.y - handler.getHeight() / 2 + e.height / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt)
    {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }
}
