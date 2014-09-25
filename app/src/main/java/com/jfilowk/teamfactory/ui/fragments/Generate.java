package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 25/09/14.
 */
public class Generate extends Fragment {

    @InjectView(R.id.setText) TextView prueba;
    Activity activity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generate, null);
        ButterKnife.inject(this, root);
        String bundle = getArguments().getString("Hola");
        prueba.setText(bundle);
        return root;
    }
}
