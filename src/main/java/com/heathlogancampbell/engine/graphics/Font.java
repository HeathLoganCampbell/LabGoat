package com.heathlogancampbell.engine.graphics;

public class Font
{
    // Font should scale
    // Font should Readable
    private static String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ " + //
            "1234567890                 "; //
    private static Bitmap fontBitMap;

    public static void init(Bitmap fontBitMap)
    {
        Font.fontBitMap = fontBitMap;
    }

    public static void text(String msg, Bitmap screen, int x, int y) {
        text(msg, screen, x, y, 0xFFFFFF);
    }

    public static void text(String msg, Bitmap screen, int x, int y, int color) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i)) % 27;
            int iy = chars.indexOf(msg.charAt(i)) / 27;
            if (ix >= 0) {
                screen.drawSegment(fontBitMap, ix * 7, 7 *  iy, 7,8, x + (i * 7), y, color);
//                screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
            }
        }
    }

    //
}
