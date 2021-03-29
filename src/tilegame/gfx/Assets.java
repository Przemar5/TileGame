package tilegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets
{
    public static Font font28;
    public static BufferedImage dirt, grass, stone, tree, wood;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
    public static BufferedImage[] zombieDown, zombieUp, zombieLeft, zombieRight;
    public static BufferedImage[] btnStart;
    public static BufferedImage inventoryScreen;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    public static void init()
    {
//        font28 = FontLoader.loadFont("/res/fonts/slkscr.ttf", 28);
        BufferedImage img = ImageLoader.loadImage("/textures/sheet.png");
        SpriteSheet sheet = new SpriteSheet(img);

        inventoryScreen = ImageLoader.loadImage("/textures/image.jpeg");

        btnStart = new BufferedImage[]{
                sheet.crop(WIDTH*2, HEIGHT*0, WIDTH, HEIGHT),
                sheet.crop(WIDTH*3, HEIGHT*0, WIDTH, HEIGHT),
        };

        playerDown = new BufferedImage[]{
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
        };
        playerUp = new BufferedImage[]{
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
        };
        playerLeft = new BufferedImage[]{
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
        };
        playerRight = new BufferedImage[]{
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
                sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT),
        };

        dirt = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        grass = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        stone = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);
        tree = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        wood = sheet.crop(WIDTH*3, HEIGHT, WIDTH, HEIGHT);
    }
}
