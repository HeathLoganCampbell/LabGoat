package com.heathlogancampbell.labgoat.level;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Font;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.commons.Velocity;
import com.heathlogancampbell.labgoat.entity.Box;
import com.heathlogancampbell.labgoat.entity.EntityBase;
import com.heathlogancampbell.labgoat.entity.PressurePlate;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Level implements Cloneable
{
    @NonNull @Getter
    private String name;
    @NonNull @Getter
    public TileBase[][] tiles;
    @NonNull
    public int[][] data;
    private boolean won = false;

    @Getter @Setter
    private int cameraX = 0;
    @Getter @Setter
    private int cameraY = 0;

    @NonNull @Getter
    private LabGoatGame game;

    @Getter
    private List<EntityBase> entities = new LinkedList<>();

    /**
     * @return has won, all presureplates are down
     */
    public boolean hasWon()
    {
        for (EntityBase entity : this.entities)
        {
            if(entity instanceof PressurePlate)
            {
                PressurePlate presurePlate = (PressurePlate) entity;
                if(!presurePlate.isActive() || !(presurePlate.getActiveEntity() instanceof Box))
                    return false;
            }
        }

        return true;
    }

    public void addEntity(EntityBase entity)
    {
        this.entities.add(entity);
    }

    public void tick(InputListener inputListener)
    {
        if(inputListener.isPressed(KeyEvent.VK_SPACE))
        {
            //reset level
            System.out.println("Level restart");
            return;
        }

        for (EntityBase entity : this.entities) {
            entity.tick(inputListener);
        }

        if(hasWon())
        {
            this.won = true;
        }

        if(inputListener.isPressed(KeyEvent.VK_UP))
        {
            this.cameraY += 2;
        }

        if(inputListener.isPressed(KeyEvent.VK_DOWN))
        {
            this.cameraY -= 2;
        }

        if(inputListener.isPressed(KeyEvent.VK_LEFT))
        {
            this.cameraX += 2;
        }

        if(inputListener.isPressed(KeyEvent.VK_RIGHT))
        {
            this.cameraX -= 2;
        }
        //move x -> y

        //Check clip
    }

    public void draw(Bitmap screen)
    {
        for (int y = 0; y < tiles.length; y++) {
            TileBase[] tileRow = tiles[y];
            for(int x = 0; x < tileRow.length; x++) {
                TileBase tile = tileRow[x];
                int tileData = data[y][x];

                tile.draw(screen, (x * TileBase.TILE_WIDTH) + cameraX, (y * TileBase.TILE_WIDTH) + cameraY, tileData);
            }
        }

        for (EntityBase entity : this.entities)
        {
            entity.draw(screen, cameraX, cameraY);
        }

        for (int y = 0; y < tiles.length; y++) {
            TileBase[] tileRow = tiles[y];
            for(int x = 0; x < tileRow.length; x++) {
                TileBase tile = tileRow[x];
                int tileData = data[y][x];

                tile.postDraw(screen, (x * TileBase.TILE_WIDTH) + cameraX, (y * TileBase.TILE_WIDTH) + cameraY, tileData);
            }
        }

        if(this.won)
        {
            Font.text("You won!", screen, 15, 15,0xFF00FF);
        }
    }

    public void pushOn(Location location, Velocity velocity, EntityBase pusherEntity)
    {
        for (EntityBase entity : this.entities) {
            if( ((int) location.getY()) == ((int) entity.getLocation().getY()) &&
                    ((int) location.getX()) == ((int) entity.getLocation().getX()) )
            {
                //found our entity
                Location newLoc = entity.getLocation().clone();
                newLoc.addVelocity(velocity);
                if(isMovable(newLoc) && entity.isPushable())
                {
                    entity.getLocation().addVelocity(velocity);
                }
            }
        }
    }

    public boolean isMovable(Location location)
    {
        if(location.getY() >= this.tiles.length) return false;
        if(location.getX() >= this.tiles[ (int) location.getY()].length) return false;
        for (EntityBase entity : this.entities) {
            if( ((int) location.getY()) == ((int) entity.getLocation().getY()) &&
                    ((int) location.getX()) == ((int) entity.getLocation().getX())
                    && entity.isSolid())
            {
                return false;
            }
        }
        return !this.tiles[(int) location.getY()][ (int) location.getX()].isSolid();
    }

    //push

    @Override
    public Level clone()
    {
        TileBase[][] tiles = Arrays.stream(this.tiles).map(TileBase[]::clone).toArray(TileBase[][]::new);
        int[][] data = Arrays.stream(this.data).map(int[]::clone).toArray(int[][]::new);
        Level level = new Level(this.name, tiles, data, this.game);
//        for (EntityBase entity : this.entities) {
//            level.addEntity(entity.clone());
//        }
        return level;
    }
}
