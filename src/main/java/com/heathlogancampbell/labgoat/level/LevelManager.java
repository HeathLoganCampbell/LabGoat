package com.heathlogancampbell.labgoat.level;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.level.format.BasicLevelFormat;
import com.heathlogancampbell.labgoat.level.format.LevelFormat;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelManager
{
    @Getter
    private LevelFormat levelFormat;
    private LabGoatGame game;
    private Bitmap bitmap;

    public LevelManager(LabGoatGame game, Bitmap bitmap)
    {
        this.levelFormat = new BasicLevelFormat();
        this.game = game;
        this.bitmap = bitmap;
    }

    public void loadLevel()
    {

    }

    public List<String> fetchLevelsInDirectory(String directoryPath)
    {
        File folder = new File(directoryPath);
        List<String> levels = new ArrayList<>();
        for (File file : folder.listFiles())
        {
            if(file.getName().endsWith(".level"))
                levels.add(file.toString());
        }
        return levels;
    }

    public String loadContext(String filePath)
    {
        String fileContent = "";
        ClassLoader classLoader = BasicLevelFormat.class.getClassLoader();
        File file = new File(filePath);
        try {
           return new String(Files.readAllBytes(file.toPath()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Level load(String filePath)
    {
        String fileContent = "";
        ClassLoader classLoader = BasicLevelFormat.class.getClassLoader();
        File file = new File(filePath);
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            return this.levelFormat.deserialize(content, this.game, this.bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Level level, String name)
    {
        ClassLoader classLoader = LevelManager.class.getClassLoader();
        File file = new File(name + ".level");

        String fileContent = this.levelFormat.serialize(level);
        Path path = Paths.get(file.getPath());

        try {
            Files.write(path, fileContent.getBytes());
            System.out.println("saved correctly");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
