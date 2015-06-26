package com.android.marco.testtesting;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display myDisplay = getWindowManager().getDefaultDisplay();
        Point dp = new Point();
        myDisplay.getSize(dp);

        Log.e("My Display is", Float.toString(Math.round(dp.x)));
        Log.e("My Display is", Float.toString(Math.round(dp.y)));

//        int width = getWindowManager().getDefaultDisplay().getWidth();
//        int height = getWindowManager().getDefaultDisplay().getHeight();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void gotoanim(View v) {
        Intent i = new Intent(this, Animations.class);
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0,0,v.getWidth(),v.getHeight());  //API 16
        startActivity(i, options.toBundle());
    }

    public void show_new(View v) {
        LinearLayout tt = (LinearLayout) findViewById(R.id.view);
        Rect r = new Rect();
        tt.getLocalVisibleRect(r);
        View c = new ProgressCycle(this,r);
        tt.addView(c);
    }

    public void gohearbeat (View v) {
        Intent i = new Intent(this, HeartBeatActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
