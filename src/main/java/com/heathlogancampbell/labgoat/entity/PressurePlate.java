package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;

public class PressurePlate extends EntityBase
{
    private LabGoatGame game;
    private Sprite sprite;
    private boolean active = false;

    public PressurePlate(LabGoatGame game, Bitmap bitmap)
    {
        this.game = game;
        this.sprite = new Sprite(bitmap, 32, 32, 0,32);
        this.setLocation(new Location(1, 1));
        this.setVelocity(new Velocity(0, 0));
    }

    @Override
    public void draw(Bitmap screen)
    {
        sprite.draw(screen, (int) (this.getLocation().getX() * EntityBase.ENTITY_WIDTH), (int) (this.getLocation().getY() * EntityBase.ENTITY_WIDTH));

        if(this.active)
        {
            screen.setPixel(0, 0, 0xFF00CC);
            screen.setPixel(0, 1, 0xFF00CC);
            screen.setPixel(0, 2, 0xFF00CC);
            screen.setPixel(1, 0, 0xFF00CC);
            screen.setPixel(2, 0, 0xFF00CC);
        }
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(game.getTick() % 10 < 5) return;
        this.active = false;
        for (EntityBase entity : this.game.getLevel().getEntities()) {
            if (((int) this.getLocation().getY()) == ((int) entity.getLocation().getY()) &&
                    ((int) this.getLocation().getX()) == ((int) entity.getLocation().getX()))
            {
                if(this != entity)
                   this.active = true;
            }
        }

    }

}
