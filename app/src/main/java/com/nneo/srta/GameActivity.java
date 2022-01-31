package com.nneo.srta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    long timeInMillisecondsNnneoSrta = 0L;
    long timeSwapBuffNnneoSrta = 0L;
    long updatedTimeNnneoSrta = 0L;
    public long startTimeNnneoSrta = 0L;
    public Handler customHandlerNnneoSrta = new Handler();
    private String timeNnneoSrta;
    private int y2NnneoSrta;
    private int y3NnneoSrta;
    private int y4NnneoSrta;
    int secsNnneoSrta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().addFlags(1024);


        metod metod = new metod();

        ImageView ballImageViewNnneoSrta2 = findViewById(R.id.ball2);
        ImageView ballImageViewNnneoSrta3 = findViewById(R.id.ball3);
        ImageView ballImageViewNnneoSrta4 = findViewById(R.id.ball4);
        Button buttonNnneoSrta = findViewById(R.id.button);
        TextView timerValueNnneoSrta = (TextView) findViewById(R.id.textView);

        Animation ballFloorAnimationDownNnneoSrta2 = AnimationUtils.loadAnimation(this, R.anim.falling_down2);
        Animation ballFloorAnimationDownNnneoSrta3 = AnimationUtils.loadAnimation(this, R.anim.falling_down3);
        Animation ballFloorAnimationDownNnneoSrta4 = AnimationUtils.loadAnimation(this, R.anim.falling_down4);


        FlingAnimation flingAnimation2
                = new FlingAnimation(ballImageViewNnneoSrta2, DynamicAnimation.Y);
        FlingAnimation flingAnimation3
                = new FlingAnimation(ballImageViewNnneoSrta3, DynamicAnimation.Y);
        FlingAnimation flingAnimation4
                = new FlingAnimation(ballImageViewNnneoSrta4, DynamicAnimation.Y);

        SpringAnimation springAnimation2
                = new SpringAnimation(ballImageViewNnneoSrta2, DynamicAnimation.X);
        SpringAnimation springAnimation3
                = new SpringAnimation(ballImageViewNnneoSrta3, DynamicAnimation.X);
        SpringAnimation springAnimation4
                = new SpringAnimation(ballImageViewNnneoSrta4, DynamicAnimation.X);

        SpringForce springForceNnneoSrta2 = new SpringForce();
        SpringForce springForceNnneoSrta3 = new SpringForce();
        SpringForce springForceNnneoSrta4 = new SpringForce();


        Runnable updateTimerThreadNnneoSrta = new Runnable() {
            public void run() {
                timeInMillisecondsNnneoSrta = SystemClock.uptimeMillis() - startTimeNnneoSrta;
                updatedTimeNnneoSrta = timeSwapBuffNnneoSrta + timeInMillisecondsNnneoSrta;
                secsNnneoSrta = (int) (updatedTimeNnneoSrta / 1000);
                int minsNnneoSrta = secsNnneoSrta / 60;
                secsNnneoSrta = secsNnneoSrta % 60;
                int millisecondsNnneoSrta = (int) (updatedTimeNnneoSrta % 1000);
                timerValueNnneoSrta.setText("" + minsNnneoSrta + ":" + String.format("%02d", secsNnneoSrta) + ":" + String.format("%03d", millisecondsNnneoSrta));
                timeNnneoSrta = ("" + minsNnneoSrta + ":" + String.format("%02d", secsNnneoSrta) + ":" + String.format("%03d", millisecondsNnneoSrta));
                customHandlerNnneoSrta.postDelayed(this, 0);
                if(secsNnneoSrta == 10){
                    ballImageViewNnneoSrta2.setVisibility(View.INVISIBLE);
                    ballImageViewNnneoSrta3.setVisibility(View.INVISIBLE);
                    ballImageViewNnneoSrta4.setVisibility(View.INVISIBLE);
                    timerValueNnneoSrta.setText("You lost");
                    startTimeNnneoSrta = 0L;
                    customHandlerNnneoSrta.removeCallbacks(this);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intentNnneoSrta = getIntent();
                    finish();
                    startActivity(intentNnneoSrta);
                }
            }
        };

        buttonNnneoSrta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewNnneoSrta) {
                startTimeNnneoSrta = SystemClock.uptimeMillis();
                customHandlerNnneoSrta.postDelayed(updateTimerThreadNnneoSrta, 0);
                viewNnneoSrta.setVisibility(View.GONE);
                ballImageViewNnneoSrta2.startAnimation(ballFloorAnimationDownNnneoSrta2);
                ballImageViewNnneoSrta3.startAnimation(ballFloorAnimationDownNnneoSrta3);
                ballImageViewNnneoSrta4.startAnimation(ballFloorAnimationDownNnneoSrta4);
                ballImageViewNnneoSrta2.setVisibility(View.VISIBLE);
                ballImageViewNnneoSrta3.setVisibility(View.VISIBLE);
                ballImageViewNnneoSrta4.setVisibility(View.VISIBLE);
            }
        });

        ballImageViewNnneoSrta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation2, springAnimation2, springForceNnneoSrta2, ballImageViewNnneoSrta2);

                int[] locationOnScreent = new int[2];
                ballImageViewNnneoSrta2.getLocationOnScreen(locationOnScreent);
                y2NnneoSrta = locationOnScreent[1];
                Log.i("MyLog", "y2: " + locationOnScreent[1]);
                if(y4NnneoSrta < 200 && y2NnneoSrta < 200 && y3NnneoSrta < 200){
                    startTimeNnneoSrta = 0L;
                    customHandlerNnneoSrta.removeCallbacks(updateTimerThreadNnneoSrta);
                    timerValueNnneoSrta.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intentNnneoSrta = getIntent();
                    finish();
                    startActivity(intentNnneoSrta);
                }
            }
        });

        ballImageViewNnneoSrta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation3, springAnimation3, springForceNnneoSrta3, ballImageViewNnneoSrta3);
                int[] locationOnScreent = new int[2];
                ballImageViewNnneoSrta3.getLocationOnScreen(locationOnScreent);
                y3NnneoSrta = locationOnScreent[1];
                Log.i("MyLog", "y3: " + locationOnScreent[1]);
                if(y4NnneoSrta < 200 && y2NnneoSrta < 200 && y3NnneoSrta < 200){
                    startTimeNnneoSrta = 0L;
                    customHandlerNnneoSrta.removeCallbacks(updateTimerThreadNnneoSrta);
                    timerValueNnneoSrta.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intentNnneoSrta = getIntent();
                    finish();
                    startActivity(intentNnneoSrta);
                }
            }
        });

        ballImageViewNnneoSrta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation4, springAnimation4, springForceNnneoSrta4, ballImageViewNnneoSrta4);

                int[] locationOnScreent = new int[2];
                ballImageViewNnneoSrta4.getLocationOnScreen(locationOnScreent);
                y4NnneoSrta = locationOnScreent[1];
                Log.i("MyLog", "y4: " + locationOnScreent[1]);
                if(y4NnneoSrta < 200 && y2NnneoSrta < 200 && y3NnneoSrta < 200){
                    startTimeNnneoSrta = 0L;
                    customHandlerNnneoSrta.removeCallbacks(updateTimerThreadNnneoSrta);
                    timerValueNnneoSrta.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intentNnneoSrta = getIntent();
                    finish();
                    startActivity(intentNnneoSrta);
                }
            }
        });
    }
}