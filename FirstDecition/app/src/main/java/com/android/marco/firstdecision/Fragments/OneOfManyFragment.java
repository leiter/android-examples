package com.android.marco.firstdecision.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.marco.firstdecision.Adapters.ListThingsAdapter;
import com.android.marco.firstdecision.Activities.MainActivity;
import com.android.marco.firstdecision.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneOfManyFragment extends Fragment {


    public OneOfManyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListThingsAdapter adapter = new ListThingsAdapter(getActivity(), getActivity().getResources(),
                R.id.lv_oneofmany_things, ((MainActivity) getActivity()).listData);
        View rootView = inflater.inflate(R.layout.fragment_one_of_many, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.lv_oneofmany_things);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_one_of_many, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
