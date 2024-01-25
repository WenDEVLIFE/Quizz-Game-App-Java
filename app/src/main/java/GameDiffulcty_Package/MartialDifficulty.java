package GameDiffulcty_Package;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.example.quiz_game_app.Menu;
import com.example.quiz_game_app.R;

import MartialLaw_Easy_Package.MartialLaw_Easy_Quiz1;
import PhilAmericanWar_Hard_Package.PH_USA_Hard_Quiz1;
import PhilAmericanWar_Normal_package.PH_USA_Normal_Quiz1;
import Philippine_American_Quiz_package.US_PH_QUIZ1;
import functions.MinimizeFunction;

public class MartialDifficulty extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private MinimizeFunction minimizeFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martial_difficulty);
        // This will play the music pilipinas kong mahal mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.bagong_lipunan);

        // This will start the music
        mediaPlayer.start();

        // This will loop the music
        mediaPlayer.setLooping(true);

        minimizeFunction = new MinimizeFunction(mediaPlayer);

        final Button button = findViewById(R.id.easybutton);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {


                // Stop the music
                minimizeFunction.StopMusic();

                Intent intent = new Intent(MartialDifficulty.this, MartialLaw_Easy_Quiz1.class);
                startActivity(intent);
                finish();
            }
        });

        final Button button2 = findViewById(R.id.normalbutton);
        button2.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // Stop the animation
                button2.clearAnimation();

                // Stop the music
                minimizeFunction.StopMusic();
                finish();
            }
        });

        final Button button3 = findViewById(R.id.hardbutton);
        button3.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {


                // Stop the music
                minimizeFunction.StopMusic();
                finish();
            }
        });

        final Button button4 = findViewById(R.id.backbutton);
        button4.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {


                // Stop the music
                minimizeFunction.StopMusic();


                // This will go back to the menu
                Intent intent = new Intent(MartialDifficulty.this, Menu.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        minimizeFunction.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        minimizeFunction.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        minimizeFunction.onDestroy();
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
}