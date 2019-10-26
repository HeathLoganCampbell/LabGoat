package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;

public class PressurePlate extends EntityBase
{
    private Sprite sprite;
    private Sprite triggerDown;
    private boolean active = false;

    public PressurePlate(LabGoatGame game, Bitmap bitmap, Location location)
    {
        super(game, EntityType.PRESSURE_PLATE, location);
        this.sprite = new Sprite(bitmap, 32, 32, 0,32);
        this.triggerDown = new Sprite(bitmap, 32, 32, 32,32);
    }

    @Override
    public void draw(Bitmap screen)
    {

        if(this.active)
        {
            triggerDown.draw(screen, (int) (this.getLocation().getX() * EntityBase.ENTITY_WIDTH), (int) (this.getLocation().getY() * EntityBase.ENTITY_WIDTH));
            screen.setPixel(0, 0, 0xFF00CC);
            screen.setPixel(0, 1, 0xFF00CC);
            screen.setPixel(0, 2, 0xFF00CC);
            screen.setPixel(1, 0, 0xFF00CC);
            screen.setPixel(2, 0, 0xFF00CC);
        }
        else
        {
            sprite.draw(screen, (int) (this.getLocation().getX() * EntityBase.ENTITY_WIDTH), (int) (this.getLocation().getY() * EntityBase.ENTITY_WIDTH));
        }
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(this.getGame().getTick() % 10 < 5) return;
        this.active = false;
        for (EntityBase entity : this.getGame().getLevel().getEntities()) {
            if (((int) this.getLocation().getY()) == ((int) entity.getLocation().getY()) &&
                    ((int) this.getLocation().getX()) == ((int) entity.getLocation().getX()))
            {
                if(this != entity)
                   this.active = true;
            }
        }

    }

}
