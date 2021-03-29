package tilegame.items;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ItemManager
{
    public Handler handler;
    public ArrayList<Item> items;
//    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
//        @Override
//        public int compare(Entity a, Entity b) {
//            if (a.y + a.height < b.y + b.height)
//                return -1;
//            return 1;
//        }
//    };
//
    public ItemManager(Handler handler)
    {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick()
    {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.tick();
            if (i.isPickedUp())
                it.remove();
        }
//        entities.sort(renderSorter);
    }

    public void render(Graphics g)
    {
        for (Item i: items) {
            i.render(g);
        }
    }

    public void addItem(Item i)
    {
        i.handler = handler;
        items.add(i);
    }
}
