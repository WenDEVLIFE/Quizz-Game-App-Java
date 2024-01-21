package functions;

import android.media.MediaPlayer;

public class MinimizeFunction {

    private MediaPlayer mediaPlayer;

    public MinimizeFunction(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void onPause() {
        PauseMusic();
    }

    public void onResume() {
        ResumeMusic();
    }

    public void onDestroy() {
        StopMusic();
    }

    private void PauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void ResumeMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void StopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}