package com.stanapps.auviz;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.preference.PreferenceManager;

public class AudioVisualizer {
    private AudioRecord mAudioRecord;
    private short[] mAudioData;
    private int mBufferSize;
    private boolean mIsRunning;
    private float[] mFrequencies;
    private Context context;
    private SharedPreferences preferences;
    private AudioManager audioManager;
    private AudioRecord audioRecord;

    private boolean isRecording;

    public AudioVisualizer(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        // Initialize AudioRecord object
        int sampleRate = 44100;
        int channelConfig = AudioFormat.CHANNEL_IN_MONO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        int bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);

        this.audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, bufferSize);

        this.isRecording = false;
    }


    public void start() {
        if (audioRecord != null && !isRecording) {
            audioRecord.startRecording();
            isRecording = true;
        }
    }

    public void stop() {
        if (audioRecord != null && isRecording) {
            audioRecord.stop();
            isRecording = false;
        }
    }

    public void release() {
        if (audioRecord != null) {
            audioRecord.release();
            audioRecord = null;
        }
    }

    public boolean isRecording() {
        return isRecording;
    }

    // ...


    public void initialize() {
        mBufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioData = new short[mBufferSize];
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, mBufferSize);
        mIsRunning = false;
        mFrequencies = new float[512];
    }


    public void setDimensions(int width, int height) {
        // TODO: implement audio visualization based on screen dimensions
    }

    public void update() {
        if (mIsRunning) {
            mAudioRecord.read(mAudioData, 0, mBufferSize);
            // TODO: implement audio processing to calculate frequencies and amplitudes
        }
    }

    public void onPause() {
        mIsRunning = false;
    }

    public void onResume() {
        mIsRunning = true;
        mAudioRecord.startRecording();
    }

    public void onDestroy() {
        mAudioRecord.stop();
        mAudioRecord.release();
    }

    public float[] getFrequencies() {
        return mFrequencies;
    }
}