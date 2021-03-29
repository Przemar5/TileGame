package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Rock;
import tilegame.entities.statics.Tree;
import tilegame.items.ItemManager;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

import java.awt.*;

public class World
{
    public Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;
    private ItemManager itemManager;

    public World(Handler handler, String path)
    {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);

        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 350));
        entityManager.addEntity(new Tree(handler, 100, 450));
        entityManager.addEntity(new Rock(handler, 100, 550));

        loadWorld(path);

        entityManager.player.x = spawnX;
        entityManager.player.y = spawnY;
    }

    public void tick()
    {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, handler.getGameCamera().xOffset / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width,
                (handler.getGameCamera().xOffset + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().yOffset / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height,
                (handler.getGameCamera().yOffset + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                getTile(x, y).render(g,
                        (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().xOffset),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().yOffset));
            }
        }
        itemManager.render(g);
        entityManager.render(g);
    }

    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.dirtTile;

        return t;
    }

    private void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[x + y*width + 4]);
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
    }
}
