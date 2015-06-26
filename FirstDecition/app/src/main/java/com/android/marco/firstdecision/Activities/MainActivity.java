package com.android.marco.firstdecision.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.marco.firstdecision.DataModels.ThingToDo;
import com.android.marco.firstdecision.Fragments.OneOfManyFragment;
import com.android.marco.firstdecision.Utils.AnimationUtil;
import com.android.marco.firstdecision.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public ArrayList<ThingToDo> listData = new ArrayList<ThingToDo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setBackgroundDrawable(null);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new OneOfManyFragment())
                    .commit();
        }
        ThingToDo t = new ThingToDo("Kino", true);
        ThingToDo f = new ThingToDo("Theater", false);
        ThingToDo g = new ThingToDo("Sport", true);
        ThingToDo h = new ThingToDo("Musik", false);

        listData.add(t);
        listData.add(f);
        listData.add(g);
        listData.add(h);

    }

    public void show_delete(View view) {
        ListView lv = (ListView) findViewById(R.id.lv_oneofmany_things);
        int f = lv.getAdapter().getCount();
        for (int i = 0; i < f; i++) {
            lv.getChildAt(i).findViewById(R.id.ib_oneofmany_delete).setVisibility(View.VISIBLE);
        }
    }

    public void animate_now(View view) {
        RelativeLayout r = (RelativeLayout) findViewById(R.id.container_rel);
        RelativeLayout.LayoutParams ll =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        List<Button> bubbleBag = new ArrayList<Button>();
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).checked) {
                Button b = new Button(this);
                b.setBackground(getResources().getDrawable(R.drawable.bubble));
                b.setLayoutParams(ll);
                b.setText(listData.get(i).title);
                bubbleBag.add(b);
                r.addView(b);
                AnimationUtil.AnimateBubbleSize(b);
            }
        }
        AnimationUtil.getRandom(0, bubbleBag.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yes_or_no, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, YesOrNoActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
