package com.heathlogancampbell.labgoat.menu;

import com.heathlogancampbell.engine.graphics.Font;
import com.heathlogancampbell.engine.graphics.Screen;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class LevelMenu extends Menu
{
    private LabGoatGame game;
    private GameMenu gameMenu;

    private int selectorIndex = 0;
    private List<String> levelFiles;
    private List<String> levelNames;


    public LevelMenu(LabGoatGame game, GameMenu gameMenu)
    {
        this.game = game;
        this.gameMenu = gameMenu;

        this.levelFiles = this.gameMenu.getLevelManager().fetchLevelsInDirectory("./");
        this.levelNames = this.levelFiles.stream().map(file -> file.replaceAll(".level", "").replaceAll("./", "")).collect(Collectors.toList());
    }

    @Override
    public void render(Screen screen)
    {
        screen.clearScreen();

        Font.text("Levels", screen, 5, 5, 0xFFFFFF);

        for(int i = 0; i < this.levelNames.size(); i++)
        {
            Font.text(levelNames.get(i), screen, 5, 15 + (7 * i), this.selectorIndex == i ? 0xff0000 : 0xFFFFFF);
        }
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(inputListener.isPressed(KeyEvent.VK_ENTER))
        {
            this.gameMenu.setLevel(this.gameMenu.getLevelManager().load(this.levelFiles.get(this.selectorIndex)));
            this.gameMenu.setLevelSerilized(this.gameMenu.getLevelManager().loadContext(this.levelFiles.get(this.selectorIndex)));
            this.game.getMenus().pop();
        }

        if(inputListener.isPressed(KeyEvent.VK_UP))
        {
            if(this.selectorIndex > 0)
                this.selectorIndex--;
            inputListener.setPressed(KeyEvent.VK_UP, false);
        }

        if(inputListener.isPressed(KeyEvent.VK_DOWN))
        {
            if(this.levelNames.size() - 1 > this.selectorIndex)
                this.selectorIndex++;
            inputListener.setPressed(KeyEvent.VK_DOWN, false);
        }
        System.out.println(this.selectorIndex);
    }
}
