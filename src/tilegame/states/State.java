package tilegame.states;

import tilegame.Handler;

import java.awt.*;

public abstract class State
{
    public static State currentState = null;
    protected Handler handler;

    public State(Handler handler)
    {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
