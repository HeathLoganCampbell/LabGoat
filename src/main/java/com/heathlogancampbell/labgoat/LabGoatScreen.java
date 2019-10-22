package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.graphics.Bitmap;
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
//        this.draw(game.getTiles(), 0, 0);
        this.drawSegment(game.getTiles(), 0, 0, 32, 32, game.getTick() % this.getWidth(), 0);
//        this.setPixel(10, 10, 0xffffff);
//        game.map.render(this, game);
    }
}
