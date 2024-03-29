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
    private String name;
    @Getter
    private Sprite sprite;
    @Getter @Setter
    private boolean solid = true;


    public TileBase(String name, Sprite sprite)
    {
        this.name = name;
        this.sprite = sprite;
    }

    public void draw(Bitmap bitmap, int x, int y, int tileData)
    {
        this.getSprite().draw(bitmap, x, y);
    }
    public void postDraw(Bitmap bitmap, int x, int y, int tileData) { }

    public static void initTiles(Bitmap bitmap)
    {
        FLOOR = new FloorTile(new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH,0));
        WALL = new WallTile(new Sprite[]{
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 0,TILE_WIDTH * 2),
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 1,TILE_WIDTH * 2),
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 2,TILE_WIDTH * 2)
                },

                new Sprite[]{
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 0,TILE_WIDTH * 3),
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 1,TILE_WIDTH * 3),
                    new Sprite(bitmap, TILE_WIDTH, TILE_WIDTH, TILE_WIDTH * 2,TILE_WIDTH * 3)
                });
    }

    public static FloorTile FLOOR;
    public static WallTile WALL;

    public static TileBase parseTile(String name)
    {
        if(name.equalsIgnoreCase("FLOOR")) return FLOOR;
        if(name.equalsIgnoreCase("WALL")) return WALL;
        return null;
    }
}
