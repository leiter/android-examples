package com.android.marco.loadlayouts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;


public class StartActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener,MyGestureFilter.MyGestureListener {

    private MyGestureFilter detector;
    private ViewGroup myContainer;
    int[] myLayoutIDs;
    private LayoutInflater myInflater;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        detector = new MyGestureFilter(this, this);
        myContainer = (ViewGroup) findViewById(R.id.container);
        myLayoutIDs = new int[] {  R.layout.aempty ,R.layout.keep_buttons_above_ime,
                R.layout.machine_welcome_page,  						// Register your layout here!
                R.layout.quiz_tab, R.layout.consult_tab };

        myInflater = (LayoutInflater)getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        spinner = (Spinner) myContainer.findViewById(R.id.outspinner);
        LayoutSpinnerAdapter myAdapter =
                new LayoutSpinnerAdapter(this, R.layout.spinner_row, prepareSpinnerData());
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void myFader(int undefined_mode) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1000);
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        spinner.setAnimation(animation);
    }

    private ArrayList<LayoutModel> prepareSpinnerData() {
        ArrayList<LayoutModel> result = new ArrayList<LayoutModel>();

        for (int t : myLayoutIDs) {
            View v = myInflater.inflate(t, null);
            LayoutModel l = new LayoutModel(getResources().getResourceName(t), t);
            result.add(l);
        }
        return result;
    }

    private View showViewForId (int id) {
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.BELOW, myContainer.getChildAt(0).getId());
        View v = myInflater.inflate(id, null);
        v.setLayoutParams(param);
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int position, long id) {
        LayoutModel l = (LayoutModel) parent.getItemAtPosition(position);
        myContainer.removeViewAt(1);
        myContainer.addView(showViewForId(l.resID));
//        myFader(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";
        switch (direction) {
            case MyGestureFilter.SWIPE_RIGHT : str = "Swipe Right";				break;
            case MyGestureFilter.SWIPE_LEFT :  str = "Swipe Left";				break;
            case MyGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                spinner.setVisibility(View.VISIBLE);                            break;
            case MyGestureFilter.SWIPE_UP :    str = "Swipe Up";
                spinner.setVisibility(View.GONE);   				            break; }

        new CustomToast(this,str).show();
    }

    @Override
    public void onDoubleTap() {
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
}
