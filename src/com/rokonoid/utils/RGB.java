package com.rokonoid.utils;

import java.awt.*;

public class RGB {
    public final int r, g, b;
    public final int rgb;
    public final int grayScale;

    public RGB() {
        this(0);
    }

    public RGB(int rgb) {
        this(new Color(rgb));
    }

    public RGB(int r, int g, int b) {
        this(new Color(r, g, b));
    }

    public RGB(Color color) {
        this.r = color.getRed();
        this.b = color.getBlue();
        this.g = color.getGreen();
        this.rgb = color.getRGB();

        grayScale = (r + g + b) / 3;
    }

    public static int toRGB(int r, int g, int b) {

        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }

    @Override
    public boolean equals(Object rgb) {
        if (rgb instanceof RGB) {
            RGB other = (RGB) rgb;
            return other.r == r && other.g == g && other.b == b;
        } else {
            return false;
        }
    }
}
