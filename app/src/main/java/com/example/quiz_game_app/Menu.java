package com.example.quiz_game_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import GameDiffulcty_Package.MartialDifficulty;
import GameDiffulcty_Package.PhDifficulty;
import functions.MinimizeFunction;

public class Menu extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private MinimizeFunction minimizeFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // This will play the music pilipinas kong mahal mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.pilipianskongmahal);

        // This will start the music
        mediaPlayer.start();

        // This will loop the music
        mediaPlayer.setLooping(true);

        minimizeFunction = new MinimizeFunction(mediaPlayer);
        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Show an alert
                AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
                alertDialog.setTitle("Alert PHI-US");
                alertDialog.setMessage("Philippine-American War started on February 4, 1899 and ended on July 2, 1902.");
                alertDialog.show();
                alertDialog.closeOptionsMenu();


                Intent intent = new Intent(Menu.this, PhDifficulty.class);
                startActivity(intent);

                // Stop the music
                StopMusic();
            }
        });

        final Button button1 = findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Show an alert
                AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
                alertDialog.setTitle("Alert ML");
                alertDialog.setMessage("Martial Law was declared on September 21, 1972 and ended on January 17, 1981.");
                alertDialog.show();
                alertDialog.closeOptionsMenu();

                Intent intent = new Intent(Menu.this, MartialDifficulty.class);
                startActivity(intent);

                // Stop the music
                StopMusic();
            }
        });


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

    // This serve as a function where the user will
    // be able to go back to the previous activity and stop the music
    protected void onPause() {
        super.onPause();
        minimizeFunction.onPause();
    }

    protected void onResume (){
        super.onResume();
        minimizeFunction.onResume();
    }

    protected void onDestroy (){
        super.onDestroy();
        minimizeFunction.onDestroy();
    }

    private void StopMusic() {
        mediaPlayer.release();
    }
}