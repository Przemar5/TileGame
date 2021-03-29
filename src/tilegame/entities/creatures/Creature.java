package tilegame.entities.creatures;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Creature extends Entity
{
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    public float speed;
    public float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
    }

    public void move()
    {
        if (!checkEntityCollision(xMove, 0f))
            moveX();
        if (!checkEntityCollision( 0f, yMove))
            moveY();
    }

    public void moveX()
    {
        if (xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            }
            else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            }
            else {
                x = (tx + 1) * Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY()
    {
        if (yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
        else if (yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            }
            else {
                y = (ty + 1) * Tile.TILE_HEIGHT - bounds.y;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y)
    {
        return handler.world.getTile(x, y).isSolid();
    }
}
