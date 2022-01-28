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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nneo.srta.R;

public class GameActivity extends AppCompatActivity {
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    public long startTime = 0L;
    public Handler customHandler = new Handler();
    private String time;
    private int y2;
    private int y3;
    private int y4;
    int secs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().addFlags(1024);


        metod metod = new metod();

        ImageView ballImageView2 = findViewById(R.id.ball2);
        ImageView ballImageView3 = findViewById(R.id.ball3);
        ImageView ballImageView4 = findViewById(R.id.ball4);
        Button button = findViewById(R.id.button);
        TextView timerValue = (TextView) findViewById(R.id.textView);

        Animation ballFloorAnimationDown2 = AnimationUtils.loadAnimation(this, R.anim.falling_down2);
        Animation ballFloorAnimationDown3 = AnimationUtils.loadAnimation(this, R.anim.falling_down3);
        Animation ballFloorAnimationDown4 = AnimationUtils.loadAnimation(this, R.anim.falling_down4);


        FlingAnimation flingAnimation2
                = new FlingAnimation(ballImageView2, DynamicAnimation.Y);
        FlingAnimation flingAnimation3
                = new FlingAnimation(ballImageView3, DynamicAnimation.Y);
        FlingAnimation flingAnimation4
                = new FlingAnimation(ballImageView4, DynamicAnimation.Y);

//        flingAnimation2.setStartVelocity(-1f);
//        flingAnimation2.setFriction(0.1f);
//        flingAnimation3.setStartVelocity(-1f);
//        flingAnimation3.setFriction(0.1f);
//        flingAnimation4.setStartVelocity(-1f);
//        flingAnimation4.setFriction(0.1f);
//        flingAnimation2.start();
//        flingAnimation3.start();
//        flingAnimation4.start();

        SpringAnimation springAnimation2
                = new SpringAnimation(ballImageView2, DynamicAnimation.X);
        SpringAnimation springAnimation3
                = new SpringAnimation(ballImageView3, DynamicAnimation.X);
        SpringAnimation springAnimation4
                = new SpringAnimation(ballImageView4, DynamicAnimation.X);

        SpringForce springForce2 = new SpringForce();
        SpringForce springForce3 = new SpringForce();
        SpringForce springForce4 = new SpringForce();

        TranslateAnimation mAnimation;

        mAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
        mAnimation.setDuration(0);

        Runnable updateTimerThread = new Runnable() {
            public void run() {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
                updatedTime = timeSwapBuff + timeInMilliseconds;
                secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (updatedTime % 1000);
                timerValue.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
                time = ("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
                customHandler.postDelayed(this, 0);
                if(secs == 10){
                    ballImageView2.startAnimation(mAnimation);
                    ballImageView2.setVisibility(View.INVISIBLE);
                    ballImageView3.setVisibility(View.INVISIBLE);
                    ballImageView4.setVisibility(View.INVISIBLE);
                    timerValue.setText("You lost");
                    startTime = 0L;
                    customHandler.removeCallbacks(this);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                view.setVisibility(View.GONE);
                ballImageView2.startAnimation(ballFloorAnimationDown2);
                ballImageView3.startAnimation(ballFloorAnimationDown3);
                ballImageView4.startAnimation(ballFloorAnimationDown4);
                ballImageView2.setVisibility(View.VISIBLE);
                ballImageView3.setVisibility(View.VISIBLE);
                ballImageView4.setVisibility(View.VISIBLE);
            }
        });

        ballImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation2, springAnimation2, springForce2, ballImageView2);

                int[] locationOnScreent = new int[2];
                ballImageView2.getLocationOnScreen(locationOnScreent);
                y2 = locationOnScreent[1];
                Log.i("MyLog", "y2: " + locationOnScreent[1]);
                if(y4 < 200 && y2 < 200 && y3 < 200){
                    startTime = 0L;
                    customHandler.removeCallbacks(updateTimerThread);
                    timerValue.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

        ballImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation3, springAnimation3, springForce3, ballImageView3);
                int[] locationOnScreent = new int[2];
                ballImageView3.getLocationOnScreen(locationOnScreent);
                y3 = locationOnScreent[1];
                Log.i("MyLog", "y3: " + locationOnScreent[1]);
                if(y4 < 200 && y2 < 200 && y3 < 200){
                    startTime = 0L;
                    customHandler.removeCallbacks(updateTimerThread);
                    timerValue.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

        ballImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metod.settings1(flingAnimation4, springAnimation4, springForce4, ballImageView4);

                int[] locationOnScreent = new int[2];
                ballImageView4.getLocationOnScreen(locationOnScreent);
                y4 = locationOnScreent[1];
                Log.i("MyLog", "y4: " + locationOnScreent[1]);
                if(y4 < 200 && y2 < 200 && y3 < 200){
                    startTime = 0L;
                    customHandler.removeCallbacks(updateTimerThread);
                    timerValue.setText("Win");
                    Toast.makeText(GameActivity.this, " Win", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
}