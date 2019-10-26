package com.heathlogancampbell.labgoat.tiles;

import com.heathlogancampbell.engine.graphics.Sprite;

public class FloorTile extends TileBase
{
    public FloorTile(Sprite sprite) {
        super("FLOOR", sprite);

        this.setSolid(false);
    }
}
