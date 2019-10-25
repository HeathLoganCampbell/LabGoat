package com.heathlogancampbell.labgoat.tiles;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import lombok.Getter;
import lombok.Setter;

/**
 * A tile is a block on the map which cannot be interactived with
 */
public class TileBase
{
    public static int TILE_WIDTH = 32;
    @Getter
    private Sprite sprite;
    @Getter @Setter
    private boolean solid = true;

    public TileBase(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void draw(Bitmap bitmap, int x, int y, int tileData)
    {
        this.getSprite().draw(bitmap, x, y);
    }

    public static void initTiles(Bitmap bitmap)
    {
        FLOOR = new FloorTile(new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH,0));
        WALL = new WallTile(new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 3,0),
                            new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 4,0));
    }

    public static FloorTile FLOOR;
    public static WallTile WALL;
}