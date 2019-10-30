package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Screen;
import com.heathlogancampbell.labgoat.menu.Menu;

/**
 * This class handles the render of the game
 */
public class LabGoatScreen  extends Screen<LabGoatGame>
{
    public LabGoatScreen(int width, int height)
    {
        super(width, height);

    }

    @Override
    public void render(LabGoatGame game)
    {
        this.clearScreen();

        Menu menu = game.getMenu().peek();
        menu.render(this);
    }
}
