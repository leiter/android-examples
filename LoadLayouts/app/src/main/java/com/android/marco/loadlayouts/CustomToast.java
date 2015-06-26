package com.android.marco.loadlayouts;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.marco.loadlayouts.R;


/**
 * Created by gen on 26.06.15.
 */
public class CustomToast extends Toast {

    private Activity mContext;
    private String mText;
    public CustomToast(Activity context,String text) {
        super(context);
        mContext=context;
        mText =text;
        prepare();

    }

    private void prepare(){
        LayoutInflater inflater = (LayoutInflater) mContext.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.custom_toast, (ViewGroup)mContext.findViewById(R.id.custom_toast_id));
        TextView t = (TextView) mView.findViewById(R.id.textView);
        t.setText(mText);
        this.setGravity(Gravity.BOTTOM, 0, 0);
        this.setDuration(Toast.LENGTH_SHORT);
        this.setView(mView);
    }
}
