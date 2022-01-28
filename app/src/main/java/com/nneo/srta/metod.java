package com.nneo.srta;

import android.widget.ImageView;

import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class metod extends GameActivity{

    public void settings1(FlingAnimation flingAnimation, SpringAnimation springAnimation, SpringForce springForce, ImageView ballImageView){
        springForce.setFinalPosition(ballImageView.getX());
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForce.setStiffness(1000f);
        springAnimation.setSpring(springForce);
        springAnimation.setStartVelocity(2000f);
        flingAnimation.setStartVelocity(-700f);
        flingAnimation.setFriction(0.5f);
        springAnimation.start();
        flingAnimation.start();

    }


}
