package com.heathlogancampbell.labgoat;

import com.google.gson.Gson;
import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.assets.Asset;
import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Font;
import com.heathlogancampbell.engine.graphics.Sprite;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.entity.Box;
import com.heathlogancampbell.labgoat.entity.Player;
import com.heathlogancampbell.labgoat.entity.PressurePlate;
import com.heathlogancampbell.labgoat.level.Level;
import com.heathlogancampbell.labgoat.level.LevelManager;
import com.heathlogancampbell.labgoat.level.format.BasicLevelFormat;
import com.heathlogancampbell.labgoat.menu.GameMenu;
import com.heathlogancampbell.labgoat.menu.MainMenu;
import com.heathlogancampbell.labgoat.menu.Menu;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.Getter;

import java.io.IOException;
import java.util.Stack;

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
    private Stack<Menu> menu = new Stack<>();

    public LabGoatGame(int width, int height)
    {
        try {
            this.tiles = Asset.loadBitmap("tiles/tiles.png");
            Font.init(Asset.loadBitmap("tiles/fonts.png"));
        } catch (IOException e) {
            System.out.println("Failed to load assets");
            e.printStackTrace();
        }
        TileBase.initTiles(this.tiles);
        menu.push(new GameMenu(this, tiles));
        menu.push(new MainMenu(this));

        //Levels

//        new BasicLevelFormat().loadLevel("levels/level1.level");

//        this.levelManager = new LevelManager(this, tiles);


//        levelManager.save(level, "YeetV1");
//        this.level = this.levelManager.load("YeetV1");
//
//        BasicLevelFormat levelFormat = new BasicLevelFormat();
//        String json = levelFormat.serialize(level);
//        this.level = levelFormat.deserialize(json, this, tiles);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        Menu menu = this.menu.peek();
        menu.tick(inputListener);

        tick++;
    }

}