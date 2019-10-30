package com.heathlogancampbell.labgoat.menu;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.engine.graphics.Font;
import com.heathlogancampbell.engine.graphics.Screen;
import com.heathlogancampbell.engine.inputs.InputListener;
import com.heathlogancampbell.labgoat.LabGoatGame;

import java.awt.event.KeyEvent;

public class MainMenu extends Menu
{
    private LabGoatGame game;

    public MainMenu(LabGoatGame game)
    {
        this.game = game;
    }

    @Override
    public void render(Screen screen)
    {
        screen.clearScreen();
        screen.setPixel(0, 0, 0xFFCC00);
        screen.setPixel(1, 0, 0xFFCC00);
        screen.setPixel(2, 0, 0xFFCC00);

        Font.text("Lab Goat", screen, 5, 5, 0xFFFFFF);
        Font.text("Hit ENTER to Play", screen, 5, 15, 0xFFFFFF);

        Font.text("Made by Heath Logan Campbell", screen, 5, screen.getHeight() - 10, 0xFFFFFF);
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
