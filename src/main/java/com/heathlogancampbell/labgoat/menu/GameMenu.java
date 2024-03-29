package com.heathlogancampbell.labgoat.menu;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Screen;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.entity.Box;
import com.heathlogancampbell.labgoat.entity.Player;
import com.heathlogancampbell.labgoat.entity.PressurePlate;
import com.heathlogancampbell.labgoat.level.Level;
import com.heathlogancampbell.labgoat.level.LevelManager;
import com.heathlogancampbell.labgoat.level.format.BasicLevelFormat;
import com.heathlogancampbell.labgoat.tiles.TileBase;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import java.awt.event.KeyEvent;
import java.util.List;

public class GameMenu extends Menu
{
    private Bitmap tiles;
    @Getter @Setter
    private Level level;
    @Getter @Setter
    public String levelSerilized;
    @Getter
    private LevelManager levelManager;
    private LabGoatGame game;


    public GameMenu(LabGoatGame game, Bitmap tiles)
    {
        this.game = game;
        this.tiles = tiles;


        this.levelManager = new LevelManager(game, tiles);
        List<String> levelNames = this.levelManager.fetchLevelsInDirectory("./");

        this.level = this.levelManager.load(levelNames.get(1));
//        this.loadBasicLevel();


//        this.levelManager = new LevelManager(this, tiles);


//        levelManager.save(level, "YeetV1");
//        this.level = this.levelManager.load("YeetV1");
    }

    @Override
    public void render(Screen screen)
    {
        this.level.draw(screen);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(inputListener.isPressed(KeyEvent.VK_SPACE))
        {
            this.level = this.getLevelManager().getLevelFormat().deserialize(this.levelSerilized, this.game, this.tiles);
            inputListener.setPressed(KeyEvent.VK_SPACE, false);
            return;
        }

        if(inputListener.isPressed(KeyEvent.VK_ESCAPE))
        {
            this.game.getMenus().push(new LevelMenu(this.game, this));
            inputListener.setPressed(KeyEvent.VK_ESCAPE, false);
            return;
        }
        this.level.tick(inputListener);
    }

    public void loadBasicLevel()
    {
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
        }, game);

        Box box = new Box(level, this.tiles, new Location(3, 2));

        Box box2 = new Box(level, tiles, new Location(2, 2));

        PressurePlate presurePlate = new PressurePlate(level, tiles, new Location(2, 1));

        this.level.addEntity(presurePlate);
        this.level.addEntity(new Player(level, tiles,  new Location(1, 1)));
        this.level.addEntity(box);
        this.level.addEntity(box2);
    }
}
