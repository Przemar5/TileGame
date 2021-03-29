package tilegame.entities;

import tilegame.Game;
import tilegame.Handler;

import java.awt.*;

public abstract class Entity
{
    public static final int DEFAULT_HEALTH = 3;
    public float x, y;
    public int width, height;
    public int health;
    public boolean active = true;
    protected Handler handler;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void hurt(int amt)
    {
        health -= amt;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public abstract void die();

    public boolean checkEntityCollision(float xOffset, float yOffset)
    {
        for (Entity e: handler.world.getEntityManager().entities) {
            if (e.equals(this))
                continue;

            if (e.getCollisionBounds(0f, 0f)
                    .intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle(
                (int) (x + bounds.x + xOffset),
                (int) (y + bounds.y + yOffset),
                bounds.width, bounds.height);
    }
}
