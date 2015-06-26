package com.android.marco.firstdecision.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.marco.firstdecision.DataModels.ThingToDo;
import com.android.marco.firstdecision.R;

import java.util.ArrayList;

/**
 * Created by gen on 02.06.15.
 */
public class ListThingsAdapter extends ArrayAdapter<ThingToDo> {

    private Activity activity;
    private ArrayList<ThingToDo> data;
    private Resources res;
    private LayoutInflater inflater;

    public ListThingsAdapter(Activity act, Resources resources, int LayoutResourceId, ArrayList objects) {
        super(act, LayoutResourceId, objects);
        activity = act;
        data = objects;
        res = resources;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent) {

        CompoundButton.OnCheckedChangeListener toggleListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(isChecked);
                data.get(position).checked = isChecked;
                notifyDataSetChanged();
            }
        };

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        };

        View row = inflater.inflate(R.layout.list_items_row, parent, false);
        ThingToDo tempValues = (ThingToDo) data.get(position);
        ToggleButton right = (ToggleButton) row.findViewById(R.id.ib_oneofmany_check);
        ImageButton left = (ImageButton) row.findViewById(R.id.ib_oneofmany_delete);
        TextView title = (TextView) row.findViewById(R.id.tv_oneofmany_title);
        left.setOnClickListener(clickListener);
        title.setText(tempValues.title);
        right.setChecked(tempValues.checked);
        right.setOnCheckedChangeListener(toggleListener);

        return row;

    }


}
