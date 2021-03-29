package tilegame.inventory;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory
{
    public Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    private int invX = 64, invY = 40, invWidth = 512, invHeight = 384,
        invListCenterX = invX + 171,
        invListCenterY = invY + invHeight/2 + 5;

    public Inventory(Handler handler)
    {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

        addItem(Item.rockItem.createNew(5));
        addItem(Item.woodItem.createNew(5));
    }

    public void tick()
    {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;

        if (!active)
            return;
    }

    public void render(Graphics g)
    {
        if (!active)
            return;

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
    }

    public void addItem(Item item)
    {
        for (Item i: inventoryItems) {
            if (i.id == item.id) {
                i.count += item.count;
                return;
            }
        }
        inventoryItems.add(item);
    }
}
