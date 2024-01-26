package MartialLaw_Hard_Package;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_game_app.Menu;
import com.example.quiz_game_app.R;

import MartialLaw_Easy_Package.MartialLaw_Easy_Quiz1;
import MartialLaw_Easy_Package.TotalScoreMartialEasy;
import functions.MinimizeFunction;

public class TotalScoreMartialHard extends AppCompatActivity {
    // variable for the music and etc for the minimize function
    private MediaPlayer mediaPlayer;
    private int total_score;

    private TextView total_score_text;

    private TextView message;

    private MinimizeFunction mininiFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_score_martial_hard);
        mediaPlayer = MediaPlayer.create(this, R.raw.totalsound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        mininiFunction = new MinimizeFunction(mediaPlayer);

        loadScore();


        final Button button = findViewById(R.id.trybutton);
        button.setOnClickListener(v -> {
            Intent intentph = new Intent(TotalScoreMartialHard.this, MartialLaw_Hard_Quiz1.class);
            startActivity(intentph);

            StopMusic();
            finish();
        });

        final Button button1 = findViewById(R.id.main_menu_button);
        button1.setOnClickListener(v -> {
            Intent intentmenu = new Intent(TotalScoreMartialHard.this, Menu.class);
            startActivity(intentmenu);

            StopMusic();
            finish();
        });
    }

    // The following methods are for the minimize function
    protected void onPause() {
        super.onPause();
        mininiFunction.onPause();

    }

    protected void onResume() {
        super.onResume();
        mininiFunction.onResume();

    }

    protected void onDestroy() {
        super.onDestroy();
        mininiFunction.onDestroy();

    }



    // Stop the music
    public void StopMusic() {
        mediaPlayer.stop();
    }


    // Load Score and message
    public void loadScore() {
        Intent intentph5 = getIntent();
        int score_received5 = intentph5.getIntExtra("score5", 0);

        total_score = score_received5;

        total_score_text = findViewById(R.id.score);
        message = findViewById(R.id.message);

        if(total_score==0){
            message.setText("       Try Again!");
            total_score_text.setText("Score: " + total_score);
        }
        else if(total_score>=1 && total_score<=3){
            message.setText("Not bad you can do it!");
            total_score_text.setText("Score: " + total_score);
        }

        else if(total_score==4){
            message.setText("1 more to go good job");
            total_score_text.setText("Score: " + total_score);
        }
        else {
            message.setText("Congratulations you got a perfect score!");
            total_score_text.setText("Score: " + total_score);
        }
    }
}