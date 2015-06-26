package com.android.marco.loadlayouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LayoutSpinnerAdapter extends ArrayAdapter<LayoutModel> {

	private StartActivity activity;
    public ArrayList<LayoutModel> data;
    private LayoutModel tempValues=null;
    private LayoutInflater inflater;

    public LayoutSpinnerAdapter(
           StartActivity activitySpinner,  int textViewResourceId, ArrayList<LayoutModel> objects )
     {
        super(activitySpinner, textViewResourceId, objects);
        activity = activitySpinner;
        data     = objects;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);         
      }
  
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        tempValues = null;
        tempValues = data.get(position);
        TextView label = (TextView) row.findViewById(R.id.layoutname);
        label.setText(tempValues.name);   	 
        return row;
      }
}
