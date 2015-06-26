package com.android.marco.loadbuttons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List buttonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonData = new ArrayList<>();
        prepareData();
        setContentView(R.layout.activity_main);
        loadButtons();
    }

    @Override
    public void onClick (View v) {
        LinearLayout myContainer = (LinearLayout)findViewById(R.id.buttonContainer);
        TextView t = (TextView) findViewById(R.id.editText1);
        for (int i=0; i < myContainer.getChildCount(); i++) {
            if (myContainer.getChildAt(i).equals(v)) {
                t.setText(Boolean.toString(((ButtonModel)buttonData.get(i)).state));
                Log.d("Hello from Button ", Integer.toString(i));}
        }
    }

    public void loadButtons() {
        LinearLayout myContainer = (LinearLayout)findViewById(R.id.buttonContainer);
        for (int i=0; i<buttonData.size(); i++) {
            Button b = new Button(this);
            b.setOnClickListener(this);
            boolean state = ((ButtonModel)buttonData.get(i)).state;
            if (state)
            { b.setBackgroundResource(R.drawable.indicator_code_lock_point_area_green_holo);}
            else {b.setBackgroundResource(R.drawable.indicator_code_lock_point_area_red_holo);}
            myContainer.addView(b);
        }
    }

    public void prepareData() {
        Random r = new Random();
        for (int i=0; i<10; i++) {
            boolean val = r.nextBoolean();
            ButtonModel b = new ButtonModel(val);
            buttonData.add(b);
        }
    }

}
