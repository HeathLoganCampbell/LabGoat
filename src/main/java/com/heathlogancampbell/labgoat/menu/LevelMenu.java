package com.heathlogancampbell.labgoat.menu;

import com.heathlogancampbell.engine.graphics.Font;
import com.heathlogancampbell.engine.graphics.Screen;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;

import java.awt.event.KeyEvent;

public class LevelMenu extends Menu
{
    private LabGoatGame game;

    public LevelMenu(LabGoatGame game)
    {
        this.game = game;
    }

    @Override
    public void render(Screen screen)
    {
        screen.clearScreen();

        Font.text("Levels", screen, 5, 5, 0xFFFFFF);
        Font.text("1", screen, 5, 15, 0xFFFFFF);
        Font.text("2", screen, 5 + 7, 15, 0xFFFFFF);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(inputListener.isPressed(KeyEvent.VK_ENTER))
        {
            this.game.getMenus().pop();
        }
    }
}
