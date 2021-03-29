package tilegame.entities;

import tilegame.Handler;
import tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager
{
    public Handler handler;
    public Player player;
    public ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.y + a.height < b.y + b.height)
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void tick()
    {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.tick();
            if (!e.active)
                it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g)
    {
        for (Entity e: entities) {
            e.render(g);
        }
        player.postRender(g);
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }
}
