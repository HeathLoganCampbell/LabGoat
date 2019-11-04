package com.heathlogancampbell.labgoat;

import com.heathlogancampbell.engine.Engine;

public class Main {

    public static void main(String[] args)
    {
        Engine<LabGoatGame> engine = new Engine<>(600, 300, 2);
        engine.setScreen(new LabGoatScreen(engine.getScreenWidth(), engine.getScreenHeight()));
        engine.setGame(new LabGoatGame(engine.getWidth(), engine.getHeight()));
        engine.setTitle("Lab Goat");
        engine.start();
    }

}
