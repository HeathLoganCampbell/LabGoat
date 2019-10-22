package com.heathlogancampbell.engine.assets;

import com.heathlogancampbell.engine.graphics.Bitmap;
import com.heathlogancampbell.labgoat.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Stream;

public class Asset
{
    public static Bitmap loadBitmap(String file) throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(file);
        BufferedImage image = ImageIO.read(stream);
        int width = image.getWidth();
        int height = image.getHeight();
        Bitmap bitmap = new Bitmap(width, height);

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
        for (int i = 0; i < pixels.length; i++) {
            bitmap.pixels[i] = (pixels[i]);
        }
        return bitmap;
    }
}
