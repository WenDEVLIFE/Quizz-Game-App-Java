package com.example.quiz_game_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import functions.MinimizeFunction;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button button;

    private MinimizeFunction minimizeFunction;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.pilipianskongmahal);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

       minimizeFunction = new MinimizeFunction(mediaPlayer);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isFinishing()) {
                    button.clearAnimation();
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Game Started");
                    alertDialog.show();
                    StopMusic();
                    Got_to_Menu();
                    finish();
                }
            }
        });

    }

    // These functions purposes are to play the music, pause the music, resume the music, and stop the music
    protected void onPause() {
        super.onPause();
        minimizeFunction.onPause();
    }

    protected  void onResume() {
        super.onResume();
        minimizeFunction.onResume();

    }

    protected  void onDestroy() {
        super.onDestroy();
        minimizeFunction.onDestroy();

    }

    private void StopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // This functuion served as a bridge to the Menu class
    private void Got_to_Menu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private void startZoomAnimation(Button button) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.2f,
                1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        button.startAnimation(scaleAnimation);
    }
}
