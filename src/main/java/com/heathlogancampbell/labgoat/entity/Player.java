package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;
import com.heathlogancampbell.labgoat.level.Level;

import java.awt.event.KeyEvent;


public class Player extends EntityBase
{
    private static final double SPEED_UNIT = 1;
    private Sprite sprite;
    private Location drawLocation;

    public Player(Level level, Bitmap bitmap, Location location)
    {
        super(level, EntityType.PLAYER, location);
        this.sprite = new Sprite(bitmap, EntityBase.ENTITY_WIDTH, EntityBase.ENTITY_WIDTH, 0,0);
    }

    @Override
    public void draw(Bitmap screen, int cameraOffsetX, int cameraOffsetY)
    {
        sprite.draw(screen, (int) (this.getLocation().getX() * ENTITY_WIDTH) + cameraOffsetX, (int) (this.getLocation().getY() * ENTITY_WIDTH) + cameraOffsetY);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(this.getVelocity().getX() == 0 && this.getVelocity().getY() == 0) {

            if (inputListener.isPressed(KeyEvent.VK_D)) {
                this.getVelocity().setX(SPEED_UNIT);
                this.getVelocity().setY(0);
                inputListener.setPressed(KeyEvent.VK_D, false);
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

        this.getLevel().pushOn(newLoc, this.getVelocity(), this);
        if(this.getLevel().isMovable(newLoc))
        {
            //0 - 8
            //1 - 8
            //is the player in the screen?
//            int diffX = this.getLevel().getCameraX() + this.getLevel().getGame().getWidth();
//            int diffY = (int) (this.getLocation().getY() * 32) - this.getLevel().getCameraY();
//
//            if(Math.abs(diffX / 32) > 7)
//                this.getLevel().setCameraX(this.getLevel().getCameraX() - (int) (32 * this.getVelocity().getX()));
////            this.getLevel().setCameraY(this.getLevel().getCameraY() - (int) (32 * this.getVelocity().getY()));


            this.getLocation().addVelocity(this.getVelocity());
        }
        this.getVelocity().clear();
    }
}
