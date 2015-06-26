package com.android.marco.testtesting;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Random;


public class Animations extends AppCompatActivity {

    private View myBackground;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        myBackground = findViewById(R.id.v_background);
    }

    public void animate_now(View v){
        int mode = getAnimMode();
        switch (mode) {
            case 0:
                myBackground.setAlpha(1.0f);
                ObjectAnimator mover = ObjectAnimator.ofFloat(myBackground,
                        "translationX", -500f, 0f);
                mover.setDuration(2000);
                mover.start();
                break;
            case 1:
                float dest = 360;
                myBackground.setAlpha(1.0f);
                if (myBackground.getRotation() == 360) {
                    System.out.println(myBackground.getAlpha());
                    dest = 0;}
                ObjectAnimator animation1 = ObjectAnimator.ofFloat(myBackground,
                        "rotation", dest);
                animation1.setDuration(2000);
                animation1.start();
                break;
            case 2:
                dest = 1;
                if (myBackground.getAlpha() > 0) {
                    dest = 0;}
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(myBackground,
                        "alpha", dest);
                animation3.setDuration(1000);
                animation3.start();
                break;
            case 3:

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_anim);
                myBackground.startAnimation(animation);
                break;

            case 4:
                AnimatorSet aSet = createAnimation();
                aSet.start();

            default:
                break;
        }
    }

    private AnimatorSet createAnimation() {
        Display d = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        int width  = p.x;    //getWindowManager().getDefaultDisplay().getWidth();
        int height = p.y;   //getWindowManager().getDefaultDisplay().getHeight();
        int nextX = random.nextInt(width);
        int nextY = random.nextInt(height);

        ObjectAnimator animation_1 = ObjectAnimator.ofFloat(myBackground, "x", nextX);
        animation_1.setDuration(1400);
        ObjectAnimator animation_2 = ObjectAnimator.ofFloat(myBackground, "y", nextY);
        animation_2.setDuration(1400);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animation_1, animation_2);

        return set;
    }

    private int getAnimMode() {
        RadioGroup r = (RadioGroup) findViewById(R.id.radio_group);
        for (int i = 0; i < r.getChildCount(); i++) {
            if (((RadioButton) r.getChildAt(i)).isChecked()) {
                return i;
            }
        }
        return -1;
    }
}
