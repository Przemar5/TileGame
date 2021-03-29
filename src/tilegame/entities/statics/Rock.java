package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.items.Item;
import tilegame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity
{
    public Rock(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void die()
    {
        handler.world.getItemManager().addItem(
                Item.rockItem.createNew((int) x, (int) y));
    }

    @Override
    public void tick()
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.stone,
                (int) (x - handler.getGameCamera().xOffset),
                (int) (y - handler.getGameCamera().yOffset),
                width, height,null);
    }
}
