package com.android.marco.firstdecision.Utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gen on 28.05.15.
 */
public class AnimationUtil {

    public static int getRandom(int start, int delta) {
        return start + (int) (Math.random() * (start + delta));
    }

    public static void AnimateBubbleSize(final View v) {
        final ObjectAnimator scaleDownX = ObjectAnimator.ofPropertyValuesHolder(v,
                PropertyValuesHolder.ofFloat("scaleX", 1.5f),
                PropertyValuesHolder.ofFloat("scaleY", 1.5f),
                PropertyValuesHolder.ofFloat("alpha", 0.3f));
        scaleDownX.setDuration(500);
        scaleDownX.setRepeatMode(ValueAnimator.REVERSE);
        scaleDownX.setRepeatCount(20);
        scaleDownX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                int m = 1, n = 1;
                if (Math.random() > 0.5) {
                    m = -1;
                }
                if (Math.random() > 0.5) {
                    n = -1;
                }
                scaleDownX.setDuration(getRandom(300, 300));
                v.animate().translationX(getRandom(m * 100, 20)).withLayer();
                v.animate().translationY(getRandom(n * 200, 20)).withLayer();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleDownX);
        animatorSet.start();
    }

    public static void FlipImageView(final Context context, final View img_bottom, final View img_top) {
        final int[] yesNoSequence = {1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1};
        final ArrayList<Drawable> imgIDs = new ArrayList<Drawable>(2);
        int repeat_count = yesNoSequence.length;
        imgIDs.add(ResourcesCompat.getDrawable(context.getResources(), android.R.drawable.star_big_on, context.getTheme()));
        imgIDs.add(ResourcesCompat.getDrawable(context.getResources(), android.R.drawable.ic_delete, context.getTheme()));

        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            int counter = 0;
            Random mRandom = new Random();

            @Override
            public void onAnimationStart(Animator animation) {
                img_top.setBackground(imgIDs.get(yesNoSequence[counter]));
                counter++;
                img_bottom.setBackground(imgIDs.get(yesNoSequence[counter]));
                counter++;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mRandom.nextBoolean()) {
                    img_bottom.setBackground(imgIDs.get(0));
                } else img_bottom.setBackground(imgIDs.get(1));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                img_top.setBackground(imgIDs.get(yesNoSequence[counter]));
                counter++;
                img_bottom.setBackground(imgIDs.get(yesNoSequence[counter]));
                counter++;
            }
        };

        final ObjectAnimator rotate_1 = ObjectAnimator.ofPropertyValuesHolder(img_top,
                PropertyValuesHolder.ofFloat("rotationX", 0f, -180f),
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f),
                PropertyValuesHolder.ofInt("startOffset", 100));  //TODO setStartoffSet
        rotate_1.setDuration(200);
        rotate_1.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate_1.setRepeatCount(repeat_count);

        final ObjectAnimator rotate_2 = ObjectAnimator.ofPropertyValuesHolder(img_bottom,
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f),
                PropertyValuesHolder.ofFloat("rotationX", 180f, 0f),
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofInt("startOffset", 100));
        rotate_2.setDuration(200);
        rotate_2.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate_2.setRepeatCount(repeat_count);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotate_1, rotate_2);
        animatorSet.addListener(listener);
        animatorSet.start();

    }


}
