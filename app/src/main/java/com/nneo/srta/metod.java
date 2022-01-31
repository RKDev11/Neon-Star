package com.nneo.srta;

import android.widget.ImageView;

import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class metod extends GameActivity{

    public void settings1(FlingAnimation flingAnimationNnneoSrta, SpringAnimation springAnimationNnneoSrta,
                          SpringForce springForceNnneoSrta, ImageView ballImageViewNnneoSrta){
        springForceNnneoSrta.setFinalPosition(ballImageViewNnneoSrta.getX());
        springForceNnneoSrta.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForceNnneoSrta.setStiffness(1000f);
        springAnimationNnneoSrta.setSpring(springForceNnneoSrta);
        springAnimationNnneoSrta.setStartVelocity(2000f);
        flingAnimationNnneoSrta.setStartVelocity(-700f);
        flingAnimationNnneoSrta.setFriction(0.5f);
        springAnimationNnneoSrta.start();
        flingAnimationNnneoSrta.start();

    }


}
