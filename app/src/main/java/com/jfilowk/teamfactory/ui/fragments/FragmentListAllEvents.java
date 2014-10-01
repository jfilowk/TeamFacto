package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jfilowk.teamfactory.R;

import butterknife.InjectView;

/**
 * Created by Javi on 01/10/14.
 */
public class FragmentListAllEvents extends Fragment {

    @InjectView(R.id.listAllEvents) ListView listAllEvents;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_all_events, null);

        return viewRoot;
    }
}
