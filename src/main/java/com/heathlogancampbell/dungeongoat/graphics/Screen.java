package com.heathlogancampbell.dungeongoat.graphics;

import com.heathlogancampbell.dungeongoat.Game;

public class Screen<G extends Game> extends Bitmap
{
	public Screen(int width, int height) {
		super(width, height);		
	}
	
	public void clearScreen()
	{
		for(int i = 0; i < this.width * this.height; i++)
			this.pixels[i] = 0;
	}
	
	public void render(G game)
	{
		this.clearScreen();
	
		for(int i = 0; i < this.area; i++)
		{
			long now = System.currentTimeMillis() + i * 16;
			int x = (int) (Math.sin((now % 2000) / 2000.0 * Math.PI * 2 ) * 40);
			int y = (int) (Math.cos((now % 2000) / 2000.0 * Math.PI * 2) * 40);
			this.setPixel(x + this.width / 2, y + this.height / 2, 0xff00ff);
		}
	}

}
