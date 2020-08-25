package com.heathlogancampbell.labgoat.tiles;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import lombok.Getter;

public class WallTile extends TileBase
{
    @Getter
    private Sprite[] wallsSprite;
    @Getter
    private Sprite[] toppers;

    public WallTile(Sprite[] wallsSprite, Sprite[] toppers) {
        super("WALL", wallsSprite[0]);

        this.wallsSprite = wallsSprite;
        this.toppers = toppers;
    }

    @Override
    public void draw(Bitmap bitmap, int x, int y, int tileData)
    {
        // Don't render
        if(((tileData >> 2) & 1) == 1)
        {
            return;
        }

        this.wallsSprite[tileData & 0b011].draw(bitmap, x, y);
    }

    @Override
    public void postDraw(Bitmap bitmap, int x, int y, int tileData)
    {
        if(((tileData >> 2) & 1) == 1)
        {
            return;
        }

        if(((tileData >> 3) & 0b11) != 0)
        {
            this.toppers[((tileData >> 3) & 0b11) - 1].draw(bitmap, x, y - TILE_WIDTH);
        }
    }

}
