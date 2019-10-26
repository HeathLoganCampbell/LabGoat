package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;

import java.awt.event.KeyEvent;

public class Player extends EntityBase
{
    private static final double SPEED_UNIT = 1;
    private Sprite sprite;
    private Location drawLocation;

    public Player(LabGoatGame game, Bitmap bitmap, Location location)
    {
        super(game, EntityType.PLAYER, location);
        this.sprite = new Sprite(bitmap, EntityBase.ENTITY_WIDTH, EntityBase.ENTITY_WIDTH, 0,0);
    }

    @Override
    public void draw(Bitmap screen)
    {
        sprite.draw(screen, (int) (this.getLocation().getX() * ENTITY_WIDTH), (int) (this.getLocation().getY() * ENTITY_WIDTH));
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(this.getVelocity().getX() == 0 && this.getVelocity().getY() == 0) {
            if (inputListener.isPressed(KeyEvent.VK_D)) {
                this.getVelocity().setX(SPEED_UNIT);
                this.getVelocity().setY(0);
                inputListener.setPressed(KeyEvent.VK_D, false);
                System.out.println("tick");
            }

            if (inputListener.isPressed(KeyEvent.VK_A)) {
                this.getVelocity().setX(-SPEED_UNIT);
                this.getVelocity().setY(0);
                inputListener.setPressed(KeyEvent.VK_A, false);
            }

            if (inputListener.isPressed(KeyEvent.VK_W)) {
                this.getVelocity().setX(0);
                this.getVelocity().setY(-SPEED_UNIT);
                inputListener.setPressed(KeyEvent.VK_W, false);
            }

            if (inputListener.isPressed(KeyEvent.VK_S)) {
                this.getVelocity().setX(0);
                this.getVelocity().setY(SPEED_UNIT);
                inputListener.setPressed(KeyEvent.VK_S, false);
            }
        }

        //
//        game.getLevel().
        //can move?
        Location newLoc = this.getLocation().clone();
        newLoc.addVelocity(this.getVelocity());

        this.getGame().getLevel().pushOn(newLoc, this.getVelocity(), this);
        if(this.getGame().getLevel().isMovable(newLoc))
        {
            this.getLocation().addVelocity(this.getVelocity());
        }
        this.getVelocity().clear();
    }
}
