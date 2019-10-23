package com.heathlogancampbell.labgoat.level;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Level
{
    private String name;
    public TileBase[][] tiles;



    public void draw(Bitmap screen)
    {
        for(int x = 0; x < tiles.length; x++) {
            TileBase[] tileRow = tiles[x];
            for (int y = 0; y < tileRow.length; y++) {
                TileBase tile = tileRow[y];

                tile.draw(screen, x * TileBase.TILE_WIDTH, y * TileBase.TILE_WIDTH);
            }
        }
    }
}
