package com.heathlogancampbell.engine.graphics;

public class Bitmap
{
	public final int width;
	public final int height;
	public final int area;
	public final int[] pixels;

	public Bitmap(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.area = this.width * this.height;
		this.pixels = new int[this.area];
	}
	
	public void draw(Bitmap bitmap, int xOffset, int yOffset)
	{
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOffset;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOffset;
				if (xPix < 0 || xPix >= width) continue;

				int src = bitmap.pixels[x + y * bitmap.width];
				pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void setPixel(int x, int y, int value)
	{
		if (y < 0 || y >= height) return;
		if (x < 0 || x >= width) return; 
		this.setPixel(x + y * this.width, value);
	}
	
	public void setPixel(int index, int value)
	{
		if (index < 0 || index >= this.area) return;
		this.pixels[index] = value;
	}

}
