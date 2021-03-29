package tilegame.items;

import tilegame.Handler;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item
{
    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32;
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.wood, "Rock", 1);
    public final int id;
    public Handler handler;
    public BufferedImage texture;
    public String name;
    public int x, y, count;
    protected Rectangle bounds;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id)
    {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
        items[id] = this;
    }

    public void tick()
    {
        if (handler.world.getEntityManager().player
                .getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.world.getEntityManager().player.inventory.addItem(this);
        }
    }

    public void render(Graphics g)
    {
        if (handler == null)
            return;

        render(g, (int) (x - handler.getGameCamera().xOffset),
                (int) (y - handler.getGameCamera().yOffset));
    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }

    public Item createNew(int count)
    {
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.count = count;
        return i;
    }

    public Item createNew(int x, int y)
    {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }
}
