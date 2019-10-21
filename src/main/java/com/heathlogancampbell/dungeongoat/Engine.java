package com.heathlogancampbell.dungeongoat;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.heathlogancampbell.dungeongoat.graphics.Screen;
import com.heathlogancampbell.dungeongoat.inputs.InputListener;

public class Engine<G extends Game> extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	
	
	private boolean running;
	private Thread thread;
	private JFrame frame;
	
	private int width;
	private int height;
	private int scale;
	
	private Screen<G> screen;
	private G game;
	private InputListener inputListener;
	
	private BufferedImage image;
	private int[] pixels;
	
	public Engine(int width, int height, int scale)
	{
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		Dimension dimesion = new Dimension(this.width * this.scale, this.height * this.scale );
		this.setPreferredSize(dimesion);
		this.setMaximumSize(dimesion);
		this.setMinimumSize(dimesion);
		
		inputListener = new InputListener();

		addKeyListener(inputListener);
		addFocusListener(inputListener);
		
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
	
		this.frame = new JFrame("Engine");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public int getScreenWidth()
	{
		return this.width;
	}
	
	public int getScreenHeight()
	{
		return this.height;
	}
	
	public int getScreenScale()
	{
		return this.scale;
	}
	
	public void setTitle(String title)
	{
		this.frame.setTitle(title);
	}
	
	public synchronized void start()
	{
		if(running) return;
		if(game == null) return;
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		this.running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		game.tick(inputListener);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		this.screen.render(this.game);

		for (int i = 0; i < this.width * this.height; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(this.image, 0, 0, this.width * this.scale, this.height * this.scale, null);
		g.dispose();
		bs.show();
		
	}
	
	public void setScreen(Screen<G> screen)
	{
		this.screen = screen;
	}
	
	public void setGame(G game) {
		this.game = game;
	}
	
	@Override
	public void run() 
	{
		int frames = 0;

		double unprocessedSeconds = 0;
		double fps = 60.0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / fps;
		int tickCount = 0;
		long maxPassTime = 100000000;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0) passedTime = 0;
			if (passedTime > maxPassTime) passedTime = maxPassTime;

			unprocessedSeconds += passedTime / 1000000000.0;

			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % fps == 0) {
					System.out.println(frames + " fps");
					lastTime += 1000;
					frames = 0;
				}
			}

			if (ticked) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
