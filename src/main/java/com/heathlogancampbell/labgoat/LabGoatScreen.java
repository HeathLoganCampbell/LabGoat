package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.graphics.Screen;

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

        this.setPixel(10, 10, 0xffffff);
//        game.map.render(this, game);
    }
}
