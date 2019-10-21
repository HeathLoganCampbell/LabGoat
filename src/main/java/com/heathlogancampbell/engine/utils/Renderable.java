package com.heathlogancampbell.engine.utils;

import com.heathlogancampbell.engine.Game;
import com.heathlogancampbell.engine.graphics.Screen;

public interface Renderable<G extends Game, S extends Screen<G>>
{
	public void render(S screen, G game);
}
