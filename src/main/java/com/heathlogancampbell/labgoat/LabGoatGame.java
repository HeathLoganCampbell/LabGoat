package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.assets.Asset;
import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
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
    private Sprite goat;
    @Getter
    private Level level;

    public LabGoatGame(int width, int height)
    {
//        this.map = new Map((int) (Math.random() * 100), width, height);
        try {
            this.tiles = Asset.loadBitmap("tiles/tiles.png");
            this.goat = new Sprite(this.tiles, 32, 32, 0,0);
        } catch (IOException e) {
            System.out.println("Failed to load assets");
            e.printStackTrace();
        }

        TileBase.initTiles(this.tiles);
        this.level = new Level("Basic", new TileBase[][]{
            { TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL},
            { TileBase.WALL, TileBase.FLOOR, TileBase.FLOOR, TileBase.WALL },
            { TileBase.WALL, TileBase.FLOOR, TileBase.FLOOR, TileBase.WALL},
            { TileBase.WALL, TileBase.WALL, TileBase.WALL, TileBase.WALL},
        });
    }

    @Override
    public void tick(InputListener inputListener)
    {
        tick++;
    }
}