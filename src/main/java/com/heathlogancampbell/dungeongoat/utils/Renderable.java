package com.heathlogancampbell.dungeongoat.utils;

import com.heathlogancampbell.dungeongoat.Game;
import com.heathlogancampbell.dungeongoat.graphics.Screen;

public interface Renderable<G extends Game, S extends Screen<G>>
{
	public void render(S screen, G game);
}
