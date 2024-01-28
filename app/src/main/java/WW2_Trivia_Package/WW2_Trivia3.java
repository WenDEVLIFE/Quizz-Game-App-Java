package WW2_Trivia_Package;

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
import android.widget.TextView;

import com.example.quiz_game_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import functions.MinimizeFunc;

public class WW2_Trivia3 extends AppCompatActivity {
    private MediaPlayer tick, correct, wrong;

    private TextView timer, correctText;
    private MediaPlayer mediaPlayer;


    private Button button, button1;

    private MinimizeFunc minimizeFunc;

    private CountDownTimer countDownTimer;

    private   int score_received3= 0;
    private ImageView timerImage;

    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ww2_trivia3);

        Intent intent = getIntent();
        score_received3 = intent.getIntExtra("score2", 0);

        // Initialize the media player
        mediaPlayer = MediaPlayer.create(this, R.raw.from_russia_with_love_huma);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        timer = findViewById(R.id.textView7);

        correctText = findViewById(R.id.score_result);

        // Start the timer
        tick = MediaPlayer.create(WW2_Trivia3.this, R.raw.countdown);
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

                wrong = MediaPlayer.create(WW2_Trivia3.this, R.raw.wrong);
                wrong.start();

                // Display to the textview that user didn't answer in time
                correctText.setText("Correct answer: You didn't answer in time!");

                timer.clearAnimation();
                timerImage.clearAnimation();

                StopMusic();
                textInputLayout.setEnabled(false);
                textInputLayout.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.VISIBLE);
            }
        }.start();

        // Get the answer from the user and check if it is correct
        textInputLayout = findViewById(R.id.textInputLayout);
        editText = textInputLayout.findViewById(R.id.answer);

        button  = findViewById(R.id.submit_button);
        button.setOnClickListener(v -> {

            String answer = Objects.requireNonNull(editText.getText()).toString();

            if (answer.equals(null)) {
                // Display to the textview that the user didn't select an answer
                AlertDialog alertDialog = new AlertDialog.Builder(WW2_Trivia3.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please enter an answer");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            } else {

                if (answer.equalsIgnoreCase("Jose Laurel")) {

                    // Display to the textview that th e user is correct and add 1 to the score variable
                    correct = MediaPlayer.create(WW2_Trivia3.this, R.raw.correctsound);
                    correct.start();

                    score_received3++;

                    correctText.setText("Your score is: " + score_received3);

                    // Make the next button visible and the submit button invisible
                    button1.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);

                    textInputLayout.setEnabled(false);
                    textInputLayout.setEnabled(false);

                    // Stop the music
                    tick.stop();
                    mediaPlayer.stop();
                    countDownTimer.cancel();
                    timerImage.clearAnimation();
                } else {
                    wrong_Method();
                }
            }
        });

        button1 = findViewById(R.id.next_button);
        button1.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(v-> {

            // Go to the next page
            Intent intentph5 = new Intent(this, WW2_Trivia4.class);
            intentph5.putExtra("score3", score_received3);
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
        wrong = MediaPlayer.create(WW2_Trivia3.this, R.raw.wrong);
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