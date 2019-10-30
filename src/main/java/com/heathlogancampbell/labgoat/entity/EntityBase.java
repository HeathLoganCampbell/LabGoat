package com.heathlogancampbell.labgoat.entity;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;
import com.heathlogancampbell.labgoat.level.Level;
import lombok.Getter;
import lombok.Setter;

/**
 * Objects on the map that aren't tied to set a set location on the map
 */
public class EntityBase
{
    public static final int ENTITY_WIDTH = 32;

    @Getter
    private EntityType entityType;
    @Getter
    @Setter
    private Location location;
    @Getter
    @Setter
    private Velocity velocity;
    @Getter
    @Setter
    private boolean solid = false;
    @Getter
    @Setter
    private boolean pushable = false;
    @Getter
    private Level level;


    public EntityBase(Level level, EntityType entityType, Location location)
    {
        this.level = level;
        this.entityType = entityType;
        this.location = location;
        this.velocity = new Velocity(0, 0);
    }

    public void draw(Bitmap screen)
    {

    }

    public void tick(InputListener inputListener)
    {

    }
}
