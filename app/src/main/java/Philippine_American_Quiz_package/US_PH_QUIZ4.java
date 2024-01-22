package Philippine_American_Quiz_package;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.quiz_game_app.R;

import functions.MinimizeFunc;

public class US_PH_QUIZ4 extends AppCompatActivity {

    private MediaPlayer tick, correct, wrong;


    private TextView timer, correctText;
    private MediaPlayer mediaPlayer;

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    private Button button, button1;

    private MinimizeFunc minimizeFunc;

    private CountDownTimer countDownTimer;

    private int score_received4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intentph = getIntent();
        int score_received3 = intentph.getIntExtra("score3", 0);

        // receive score from previous activity
        score_received4 = score_received3 ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_ph_quiz4);


        mediaPlayer = MediaPlayer.create(this, R.raw.johnwickksong);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);



        timer = findViewById(R.id.textView7);

        correctText = findViewById(R.id.score_result);

        // Start the timer
        tick = MediaPlayer.create(US_PH_QUIZ4.this, R.raw.countdown);
        minimizeFunc = new MinimizeFunc(mediaPlayer,tick, correct, wrong);
        countDownTimer = new CountDownTimer( 30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Time: " + millisUntilFinished / 1000);
                if (!tick.isPlaying()){
                    tick.start();
                }
            }

            public void onFinish() {
                timer.setText("Time's up!");
                tick.stop();

                wrong = MediaPlayer.create(US_PH_QUIZ4.this, R.raw.wrong);
                wrong.start();

                // Display to the textview that user didn't answer in time
                correctText.setText("Correct answer: You didn't answer in time!");

                StopMusic();
            }
        }.start();

        radioButton1 = findViewById(R.id.radioButton);
        radioButton1.setOnClickListener( v -> {

            // Uncheck the other radio buttons
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
            radioButton4.setChecked(false);

        });
        radioButton2 = findViewById(R.id.radioButton1);
        radioButton2.setOnClickListener( v -> {

            // Uncheck the other radio buttons
            radioButton3.setChecked(false);
            radioButton4.setChecked(false);
            radioButton1.setChecked(false);

        });
        radioButton3 = findViewById(R.id.radioButton2);
        radioButton3.setOnClickListener( v -> {

            // Uncheck the other radio buttons
            radioButton4.setChecked(false);
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);

        });
        radioButton4 = findViewById(R.id.radioButton3);
        radioButton4.setOnClickListener( v -> {

            // Uncheck the other radio buttons
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);


        });

        button = findViewById(R.id.submit_button);
        button.setOnClickListener(v -> {

            if (radioButton1.isChecked()) {
                wrong_Method();
            }
            else if (radioButton2.isChecked()) {
                wrong_Method();
            }
            else if (radioButton3.isChecked()) {
                wrong_Method();
            }
            else if (radioButton4.isChecked()) {
                score_received4++;
                correct = MediaPlayer.create(US_PH_QUIZ4.this, R.raw.correctsound);
                correct.start();
                tick.stop();
                countDownTimer.cancel();


                // Display to the textview that user got the wrong answer
                correctText.setText("Correct answer: Correct you have score" + score_received4);

                StopMusic();
                button1.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
            else {
                wrong_Method();
            }
        });

        button1 = findViewById(R.id.next_button);
        button1.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(v-> {

            // Go to the next page
            nextPage();
        });


    }


    // The following methods are for the minimize function
    protected void onPause() {
        super.onPause();
        minimizeFunc.onPause();
        countDownTimer.cancel();
    }

    protected void onResume() {
        super.onResume();
        minimizeFunc.onResume();
        countDownTimer.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        minimizeFunc.onDestroy();
        countDownTimer.cancel();
    }


    // Stop the music
    public void StopMusic() {
        mediaPlayer.stop();
    }


    // Wrong method for wrong answers
    public void wrong_Method(){
        wrong = MediaPlayer.create(US_PH_QUIZ4.this, R.raw.wrong);
        wrong.start();

        // Display to the textview that user didn't select an answer
        correctText.setText("Correct answer: You are wrong!");

        StopMusic();
        tick.stop();
        countDownTimer.cancel();
        button1.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
    }
    public void nextPage(){

        // Go to the next page
        Intent intent = new Intent(this, US_PH_QUIZ5.class);
        intent.putExtra("score4", score_received4);
        startActivity(intent);

        finish();
    }
}