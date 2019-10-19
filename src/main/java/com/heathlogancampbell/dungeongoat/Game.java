package com.heathlogancampbell.dungeongoat;

public class Game implements Runnable
{
    public static final int TICKS = 20;
    public static final int FPS = 30;
    public static final boolean TIMINGS = true;
    private boolean running = true;

    public Game()
    {
        this.run();
    }

    private void getInput()
    {

    }

    private void update()
    {

    }

    private void render()
    {
    }


    public void run()
    {
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / TICKS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                getInput();
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (TIMINGS) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }

    }
}
