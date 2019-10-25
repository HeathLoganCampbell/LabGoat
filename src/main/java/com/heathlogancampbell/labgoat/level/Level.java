package com.heathlogancampbell.labgoat.level;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.entity.EntityBase;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Level
{
    @NonNull
    private String name;
    @NonNull
    public TileBase[][] tiles;
    @NonNull
    public int[][] data;

    private List<EntityBase> entities = new LinkedList<>();

    public void addEntity(EntityBase entity)
    {
        this.entities.add(entity);
    }

    public void tick(InputListener inputListener)
    {
        for (EntityBase entity : this.entities) {
            entity.tick(inputListener);
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


                tile.draw(screen, x * TileBase.TILE_WIDTH, y * TileBase.TILE_WIDTH, tileData);
            }
        }

        for (EntityBase entity : this.entities)
        {
            entity.draw(screen);
        }
    }

    public boolean isMovable(Location location)
    {
        if(location.getY() >= this.tiles.length) return false;
        if(location.getX() >= this.tiles[ (int) location.getY()].length) return false;
        return !this.tiles[(int) location.getY()][ (int) location.getX()].isSolid();
    }

    //push
}
