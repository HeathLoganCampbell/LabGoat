package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;
import com.heathlogancampbell.labgoat.level.Level;

public class Box extends EntityBase
{
    private Sprite sprite;

    public Box(Level level, Bitmap bitmap, Location location)
    {
        super(level, EntityType.BOX, location);
        this.sprite = new Sprite(bitmap, 32, 32, 32 * 2,0);
        this.setSolid(true);
        this.setPushable(true);
    }

    @Override
    public void draw(Bitmap screen, int cameraOffsetX, int cameraOffsetY)
    {
        sprite.draw(screen, (int) (this.getLocation().getX() * EntityBase.ENTITY_WIDTH) + cameraOffsetX, (int) (this.getLocation().getY() * EntityBase.ENTITY_WIDTH) + cameraOffsetY);
    }

    @Override
    public void tick(InputListener inputListener)
    {

    }

}
