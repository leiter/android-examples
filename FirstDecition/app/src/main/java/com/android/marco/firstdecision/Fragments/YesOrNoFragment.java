package com.android.marco.firstdecision.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.marco.firstdecision.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YesOrNoFragment extends Fragment {


    public YesOrNoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yes_or_no, container, false);
    }


}
