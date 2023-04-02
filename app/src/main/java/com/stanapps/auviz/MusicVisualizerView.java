package com.stanapps.auviz;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MusicVisualizerView extends GLSurfaceView {
    private MusicVisualizerRenderer mRenderer;

    public MusicVisualizerView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new MusicVisualizerRenderer(context);
        setRenderer(mRenderer);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
    }

    public void updateOffsets(float xOffset, float yOffset) {
        mRenderer.updateOffsets(xOffset, yOffset);
    }

    public void updateDimensions(int width, int height) {
        mRenderer.updateDimensions(width, height);
    }

    public void onPause() {
        super.onPause();
        mRenderer.onPause();
    }

    public void onResume() {
        super.onResume();
        mRenderer.onResume();
    }

    public void onDestroy() {
        super.onDetachedFromWindow();
        mRenderer.onDestroy();
    }

}