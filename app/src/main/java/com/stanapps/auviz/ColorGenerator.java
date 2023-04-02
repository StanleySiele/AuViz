package com.stanapps.auviz;

public class ColorGenerator {
    private int mWidth, mHeight;
    private int[] mColors;


    public void setDimensions(int width, int height) {

        mWidth = width;
        mHeight = height;
        mColors = new int[mWidth * mHeight];
    }

    public void update(float[] frequencies) {
        // TODO: implement color generation based on frequencies and amplitudes
    }

    public void render(float xOffset, float yOffset) {
        // TODO: implement rendering of colors to the wallpaper surface
    }


}


