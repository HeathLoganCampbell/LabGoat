package com.heathlogancampbell.labgoat.level.format;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.labgoat.LabGoatGame;
import com.heathlogancampbell.labgoat.commons.Location;
import com.heathlogancampbell.labgoat.entity.EntityBase;
import com.heathlogancampbell.labgoat.entity.EntityType;
import com.heathlogancampbell.labgoat.level.Level;
import com.heathlogancampbell.labgoat.tiles.TileBase;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BasicLevelFormat extends LevelFormat
{
    @Override
    public Level deserialize(String input, LabGoatGame game, Bitmap bitmap)
    {
        Level level = null;
        System.out.println(input);
//        ClassLoader classLoader = BasicLevelFormat.class.getClassLoader();
//        File file = new File(classLoader.getResource(input).getFile());
//        try {
//            String content = new String(Files.readAllBytes(file.toPath()));
        List<TileBase[]> mapTiles = new ArrayList<>();
        List<EntityBase> entities = new ArrayList<>();
        boolean entitiesSection = false;
        String[] split = input.split("\n");
        for(String row : split)
        {
            if(row.equalsIgnoreCase("entities"))
            {
                entitiesSection = true;
                continue;
            }

            if(!entitiesSection) {
                List<TileBase> tileRow = new ArrayList<>();
                for (String tileStr : row.split(",")) {
                    if (tileStr == null || tileStr == "") continue;
                    TileBase tileBase = TileBase.parseTile(tileStr);
                    if (tileBase == null) continue;
                    tileRow.add(tileBase);
                }

                if (tileRow.size() >= 1)
                    mapTiles.add((TileBase[]) tileRow.toArray(new TileBase[tileRow.size()]));
            }
            else
            {
                //PLAYER (1.0, 1.0)
                if (row == null || row == "") continue;
                String[] parts = row.split(" ");
                EntityType entityType = EntityType.valueOf(parts[0]);
                String locStr = parts[1].replaceAll("\\(", "").replaceAll("\\)", "");
                System.out.println(locStr);
                String[] locArgs = locStr.split(",");
                double x = Double.parseDouble(locArgs[0]);
                double y = Double.parseDouble(locArgs[1]);

                try {
                    Location loc = new Location(x, y);
                    Constructor<?> constructorEntity = entityType.getClazz().getConstructor(LabGoatGame.class, Bitmap.class, Location.class);
                    EntityBase entitybase = (EntityBase) constructorEntity.newInstance(game, bitmap, loc);
                    entities.add(entitybase);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }


            }
        }

        TileBase[][] tiles = new TileBase[mapTiles.size()][mapTiles.get(0).length];
        for(int i = 0; i < mapTiles.size(); i++)
            tiles[i] = mapTiles.get(i);

        level = new Level("derp", tiles,new int[tiles.length][tiles[0].length], game);
        entities.forEach(level::addEntity);

        return level;
    }

    @Override
    public String serialize(Level level)
    {
        String serilize = "";
        for (int y = 0; y < level.getTiles().length; y++) {
            TileBase[] tileRow = level.getTiles()[y];
            for(int x = 0; x < tileRow.length; x++) {
                TileBase tile = tileRow[x];
                serilize += tile.getName() + ",";
            }
            serilize +=  "\n";
        }
        serilize += "ENTITIES\n";
        for(EntityBase entity : level.getEntities())
        {
            serilize += entity.getEntityType().name() + " (" + entity.getLocation().getX() + "," + entity.getLocation().getY() + ")\n";
        }

        return serilize;
    }
}
