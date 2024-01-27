package WW2_Spelling_Package;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCodecInfo;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import MartialLaw_Easy_Package.MartialLaw_Easy_Quiz1;
import MartialLaw_Easy_Package.MartialLaw_Easy_Quiz2;
import functions.MinimizeFunc;

public class WW2_Spelling1 extends AppCompatActivity {
    private MediaPlayer tick, correct, wrong;

    private TextView timer, correctText;
    private MediaPlayer mediaPlayer;


    private Button button, button1;

    private MinimizeFunc minimizeFunc;

    private CountDownTimer countDownTimer;

    private   int score_received1= 0;
    private ImageView timerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ww2_spelling1);
        mediaPlayer = MediaPlayer.create(this, R.raw.from_russia_with_love_huma);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        timer = findViewById(R.id.textView7);

        correctText = findViewById(R.id.score_result);

        // Start the timer
        tick = MediaPlayer.create(WW2_Spelling1.this, R.raw.countdown);
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

                wrong = MediaPlayer.create(WW2_Spelling1.this, R.raw.wrong);
                wrong.start();

                // Display to the textview that user didn't answer in time
                correctText.setText("Correct answer: You didn't answer in time!");

                timer.clearAnimation();

                StopMusic();
            }
        }.start();

        button  = findViewById(R.id.submit_button);
        button.setOnClickListener(v -> {
            TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
            TextInputEditText editText = textInputLayout.findViewById(R.id.answer);

            String answer = Objects.requireNonNull(editText.getText()).toString();

            if (answer != null) {
                if (answer.equalsIgnoreCase("Japanese")) {
                    correct = MediaPlayer.create(WW2_Spelling1.this, R.raw.correctsound);
                    correct.start();
                    score_received1 = 1;
                    correctText.setText("Your score is: " + score_received1);
                    button1.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    tick.stop();
                    mediaPlayer.stop();
                } else {
                    wrong_Method();
                }
            } else {
                // Display to the textview that the user didn't select an answer
                AlertDialog alertDialog = new AlertDialog.Builder(WW2_Spelling1.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please enter an answer");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            }
        });

        button1 = findViewById(R.id.next_button);
        button1.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(v-> {

            // Go to the next page
            Intent intentph5 = new Intent(this, MartialLaw_Easy_Quiz2.class);
            intentph5.putExtra("score1", score_received1);
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
        wrong = MediaPlayer.create(WW2_Spelling1.this, R.raw.wrong);
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