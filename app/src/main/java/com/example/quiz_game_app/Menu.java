package com.example.quiz_game_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import org.w3c.dom.Node;

public class Menu extends AppCompatActivity {
    private MediaPlayer mediaPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.pilipianskongmahal);

        mediaPlayer1.start();
        mediaPlayer1.setLooping(true);

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Game Started");
                alertDialog.show();


                // Stop the music
                StopMusic();


            }
        });

        startZoomAnimation(button);




    final Button button1 = findViewById(R.id.button3);
          button1.setOnClickListener(new View.OnClickListener()
    {
        public void onClick (View v) {
            // Code here executes on main thread after user presses button

            AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Game Started");
            alertDialog.show();


            // Stop the music
            StopMusic();

        }
    });

    startZoomAnimation(button1);

}

    private void startZoomAnimation(final View view) {
        // Morph Animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        view.startAnimation(scaleAnimation);
    }

    private void StopMusic() {
        mediaPlayer1.stop();
        mediaPlayer1.release();
    }
}