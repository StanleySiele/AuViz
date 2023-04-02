package com.stanapps.auviz;

import android.content.Context;
import android.media.AudioManager;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class MusicVisualizerLiveWallpaper extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MusicVisualizerEngine();
    }

    private class MusicVisualizerEngine extends Engine {
        private MusicVisualizerView mVisualizerView;
        private AudioManager audioManager;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            mVisualizerView = new MusicVisualizerView(getApplicationContext());
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mVisualizerView.onDestroy();
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            mVisualizerView.onResume();
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            mVisualizerView.onPause();
        }

        @Override
        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
            mVisualizerView.updateOffsets(xOffset, yOffset);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            mVisualizerView.updateDimensions(width, height);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            if (visible) {
                mVisualizerView.onResume();
            } else {
                mVisualizerView.onPause();
            }
        }
    }
}

