package com.example.quiz_game_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.pilipianskongmahal);

        mediaPlayer.start();
        mediaPlayer.setLooping(true);




       final Button button = findViewById(R.id.button);
         button.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                // Code here executes on main thread after user presses button

                  AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Game Started");
                    alertDialog.show();

                    Got_to_Menu();


                    // Stop the music
                  StopMusic();



              }
         });

         startZoomAnimation(button);


    }


    // Zoom Animation of the button
    private void startZoomAnimation(Button button) {
        // Morph Animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.2f,
                1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);   // Set the duration of the animation in milliseconds
        scaleAnimation.setRepeatCount(Animation.INFINITE); // Repeat animation indefinitely
        scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will zoom back out

        button.startAnimation(scaleAnimation);
    }

    private void StopMusic() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void Got_to_Menu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

}