package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;

public class Box extends EntityBase
{
    private LabGoatGame game;
    private Sprite sprite;

    public Box(LabGoatGame game, Bitmap bitmap)
    {
        this.game = game;
        this.sprite = new Sprite(bitmap, 32, 32, 32 * 2,0);
        this.setLocation(new Location(1, 1));
        this.setVelocity(new Velocity(0, 0));
        this.setSolid(true);
    }

    @Override
    public void draw(Bitmap screen)
    {
        sprite.draw(screen, (int) (this.getLocation().getX() * EntityBase.ENTITY_WIDTH), (int) (this.getLocation().getY() * EntityBase.ENTITY_WIDTH));
    }

    @Override
    public void tick(InputListener inputListener)
    {

    }

}
