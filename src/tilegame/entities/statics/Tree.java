package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.items.Item;
import tilegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity
{
    public Tree(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 20;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 44;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void die()
    {
        handler.world.getItemManager().addItem(
                Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void tick()
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.tree,
                (int) (x - handler.getGameCamera().xOffset),
                (int) (y - handler.getGameCamera().yOffset),
                width, height,null);
    }
}
