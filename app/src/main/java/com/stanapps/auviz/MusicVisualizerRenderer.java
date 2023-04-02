package com.stanapps.auviz;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MusicVisualizerRenderer implements GLSurfaceView.Renderer {
    private Context mContext;
    private AudioVisualizer mAudioVisualizer;
    private ColorGenerator mColorGenerator;
    private float mXOffset, mYOffset;
    private int mWidth, mHeight;

    public MusicVisualizerRenderer(Context context) {
        mContext = context;
        mAudioVisualizer = new AudioVisualizer(context);
        mColorGenerator = new ColorGenerator();
    }

    public void updateOffsets(float xOffset, float yOffset) {
        mXOffset = xOffset;
        mYOffset = yOffset;
    }

    public void updateDimensions(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public void onPause() {
        mAudioVisualizer.onPause();
    }

    public void onResume() {
        mAudioVisualizer.onResume();
    }

    public void onDestroy() {
        mAudioVisualizer.onDestroy();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mAudioVisualizer.initialize();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mAudioVisualizer.setDimensions(width, height);
        mColorGenerator.setDimensions(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mAudioVisualizer.update();
        mColorGenerator.update(mAudioVisualizer.getFrequencies());
        mColorGenerator.render(mXOffset, mYOffset);
    }
}

