package com.heathlogancampbell.labgoat.level.format;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.level.Level;

public abstract class LevelFormat
{
    public abstract Level deserialize(String input, LabGoatGame game, Bitmap bitmap);
    public abstract String serialize(Level level);
}
