package com.heathlogancampbell.engine.graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Sprite
{
    @Getter
    private Bitmap bitmap;
    @Getter
    private int width, height;
    //Where on the image is the x & y
    @Getter
    private int innerX, innerY;

    /**
     * Draws sprite to a location on bitmap
     * @param screen
     * @param x
     * @param y
     */
    public void draw(Bitmap screen, int x, int y)
    {
        screen.drawSegment(this.getBitmap(), innerX, innerY, width, height, x, y);
    }
}
