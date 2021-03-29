package tilegame.entities.creatures;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
    public Inventory inventory;
    private Animation animDown, animUp, animLeft, animRight;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH,
                        Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 35;
        bounds.width = 24;
        bounds.height = 27;

        animDown = new Animation(500, Assets.playerDown);
        animUp = new Animation(500, Assets.playerUp);
        animLeft = new Animation(500, Assets.playerLeft);
        animRight = new Animation(500, Assets.playerRight);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick()
    {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks();

        inventory.tick();
    }

    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y + arSize;
        }
        else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y + cb.height;
        }
        else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x + arSize;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else {
            return;
        }

        attackTimer = 0;

        for (Entity e: handler.world.getEntityManager().entities) {
            if (e.equals(this))
                continue;

            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die()
    {
        System.out.println("You lose");
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up)
            yMove = -speed;

        if (handler.getKeyManager().down)
            yMove = +speed;

        if (handler.getKeyManager().left)
            xMove = -speed;

        if (handler.getKeyManager().right)
            xMove = +speed;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().xOffset),
                (int) (y - handler.getGameCamera().yOffset),
                width, height, null);

        inventory.render(g);
    }

    public void postRender(Graphics g)
    {
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        }
        else if (xMove > 0) {
            return animRight.getCurrentFrame();
        }
        else if (yMove < 0) {
            return animUp.getCurrentFrame();
        }
        else if (yMove > 0) {
            return animDown.getCurrentFrame();
        }
        else {
            return animDown.getCurrentFrame();
        }
    }
}
