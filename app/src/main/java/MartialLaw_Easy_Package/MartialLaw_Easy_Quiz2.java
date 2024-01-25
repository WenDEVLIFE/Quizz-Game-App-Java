package MartialLaw_Easy_Package;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.quiz_game_app.R;

import functions.MinimizeFunc;

public class MartialLaw_Easy_Quiz2 extends AppCompatActivity {
    private MediaPlayer tick, correct, wrong;

    private TextView timer, correctText;
    private MediaPlayer mediaPlayer;

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    private Button button, button1;

    private MinimizeFunc minimizeFunc;

    private CountDownTimer countDownTimer;

    private   int score_received2= 0;
    private ImageView timerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martial_law_easy_quiz2);
        mediaPlayer = MediaPlayer.create(this, R.raw.from_russia_with_love_huma);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

         Intent intent = getIntent();
        int score_received1 = intent.getIntExtra("score1", 0);
        score_received2 = score_received1;

        timer = findViewById(R.id.textView7);

        correctText = findViewById(R.id.score_result);

        // Start the timer
        tick = MediaPlayer.create(MartialLaw_Easy_Quiz2.this, R.raw.countdown);
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

                wrong = MediaPlayer.create(MartialLaw_Easy_Quiz2.this, R.raw.wrong);
                wrong.start();

                // Display to the textview that user didn't answer in time
                correctText.setText("Correct answer: You didn't answer in time!");

                timer.clearAnimation();

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

            timer.clearAnimation();

            if (radioButton1.isChecked()) {

                wrong_Method();
            }
            else if (radioButton2.isChecked()) {
                score_received2++;
                correct = MediaPlayer.create(MartialLaw_Easy_Quiz2.this, R.raw.correctsound);
                correct.start();
                tick.stop();
                countDownTimer.cancel();


                // Display to the textview that user got the wrong answer
                correctText.setText("Correct answer: Correct you have score " +  score_received2);

                StopMusic();
                button1.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
            else if (radioButton3.isChecked()) {
                wrong_Method();
            }
            else if (radioButton4.isChecked()) {
                wrong_Method();
            }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(MartialLaw_Easy_Quiz2.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please select an answer!");
                alertDialog.show();

            }
        });

        button1 = findViewById(R.id.next_button);
        button1.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(v-> {

            // Go to the next page
            Intent intentph5 = new Intent(this, MartialLaw_Easy_Quiz3.class);
            intentph5.putExtra("score2", score_received2);
            startActivity(intentph5);

            // Reset the score
            finish();
        });

        timerImage = findViewById(R.id.imageView3);

        startZoomAnimation(timerImage);
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
        wrong = MediaPlayer.create(MartialLaw_Easy_Quiz2.this, R.raw.wrong);
        wrong.start();

        // Display to the textview that user didn't select an answer
        correctText.setText("Correct answer: You are wrong!");

        StopMusic();
        tick.stop();
        countDownTimer.cancel();
        button1.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
    }

    private void startZoomAnimation(ImageView timerImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.2f,
                1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        timerImage.startAnimation(scaleAnimation);
    }
}