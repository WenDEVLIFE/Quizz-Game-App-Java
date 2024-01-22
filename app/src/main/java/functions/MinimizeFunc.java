package functions;

import android.media.MediaPlayer;

public class MinimizeFunc {
    private MediaPlayer tick;
    private MediaPlayer correct;

    private MediaPlayer wrong;

    private MediaPlayer mediaPlayer;

    public MinimizeFunc(MediaPlayer mediaPlayer , MediaPlayer tick, MediaPlayer correct, MediaPlayer wrong){
        this.tick = tick;
        this.correct = correct;
        this.wrong = wrong;
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
        if (mediaPlayer != null && mediaPlayer.isPlaying()  && tick.isPlaying() && correct.isPlaying() && wrong.isPlaying() ) {
            mediaPlayer.pause();
            tick.pause();

        }

    }

    private void ResumeMusic() {

        if (mediaPlayer != null && !mediaPlayer.isPlaying()  && !tick.isPlaying() && !correct.isPlaying() && !wrong.isPlaying() ) {
            mediaPlayer.start();
            tick.start();

        }

    }

    private void StopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()  && tick.isPlaying() && correct.isPlaying() && wrong.isPlaying() ) {
            mediaPlayer.release();
            mediaPlayer = null;
            tick.release();
            tick = null;


        }

    }

}
