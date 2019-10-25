package com.heathlogancampbell.labgoat.tiles;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Sprite;
import lombok.Getter;

public class WallTile extends TileBase
{
    @Getter
    private Sprite walledSprite;

    public WallTile(Sprite flatSprite, Sprite walledSprite) {
        super(flatSprite);

        this.walledSprite = walledSprite;
    }

    @Override
    public void draw(Bitmap bitmap, int x, int y, int tileData)
    {
        if(((tileData >> 1) & 1) == 0)
        {
            this.getSprite().draw(bitmap, x, y);
        }
        else {
            walledSprite.draw(bitmap, x, y);
        }
    }
}
