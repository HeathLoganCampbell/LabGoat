package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.assets.Asset;
import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.entity.Box;
import com.heathlogancampbell.labgoat.entity.Player;
import com.heathlogancampbell.labgoat.entity.PressurePlate;
import com.heathlogancampbell.labgoat.level.Level;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.Getter;

import java.io.IOException;

/**
 * This class handles the state of the game
 */
public class LabGoatGame extends Game
{
    @Getter
    private Bitmap tiles;
    @Getter
    private int tick = 0;
    @Getter
    private Level level;

    public LabGoatGame(int width, int height)
    {
//        this.map = new Map((int) (Math.random() * 100), width, height);
        try {
            this.tiles = Asset.loadBitmap("tiles/tiles.png");
        } catch (IOException e) {
            System.out.println("Failed to load assets");
            e.printStackTrace();
        }

        TileBase.initTiles(this.tiles);
        this.level = new Level("Basic", new TileBase[][]{
            { TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL},
            { TileBase.WALL, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.WALL },
            { TileBase.WALL, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.WALL },
            { TileBase.WALL, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.FLOOR, TileBase.WALL },
            { TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL},
        },
            new int[][]{
                    { 0b0010, 0, 0, 0, 0, 0, 0b0010},
                    { 0b0010, 0, 0, 0, 0, 0, 0b0010},
                    { 0b0010, 0, 0, 0, 0, 0, 0b0010},
                    { 0b0010, 0, 0, 0, 0, 0, 0b0010},
                    { 0, 0, 0, 0, 0, 0, 0},
            });

        Box box = new Box(this, tiles);
        box.setLocation(new Location(3, 2));

        Box box2 = new Box(this, tiles);
        box2.setLocation(new Location(2, 2));

        PressurePlate presurePlate = new PressurePlate(this, tiles);
        presurePlate.getLocation().setX(2);

        this.level.addEntity(presurePlate);
        this.level.addEntity(new Player(this, tiles));
        this.level.addEntity(box);
        this.level.addEntity(box2);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        this.getLevel().tick(inputListener);

        tick++;
    }
}