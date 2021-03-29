package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.ui.ClickListener;
import tilegame.ui.UIImageButton;
import tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State
{
    private UIManager uiManager;

    public MenuState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btnStart, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.currentState = handler.game.gameState;
            }
        }));
    }

    @Override
    public void tick()
    {
        uiManager.tick();

        handler.getMouseManager().setUiManager(null);
        State.currentState = handler.game.gameState;
    }

    @Override
    public void render(Graphics g)
    {
        uiManager.render(g);
    }
}
