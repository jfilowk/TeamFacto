package com.jfilowk.teamfactory.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jfilowk.teamfactory.R;

/**
 * Created by Javi on 25/09/14.
 */
public class FragmentInitProgress extends Fragment {

    public FragmentInitProgress() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_progress, null);

        return root;
    }
}
