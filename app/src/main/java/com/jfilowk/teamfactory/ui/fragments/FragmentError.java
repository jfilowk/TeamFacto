package com.jfilowk.teamfactory.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jfilowk.teamfactory.R;

/**
 * Created by Javi on 08/10/14.
 */
public class FragmentError extends Fragment {

    public static FragmentError newInstance (){
        FragmentError fragmentError = new FragmentError();

        return fragmentError;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_error, null);

        return root;
    }
}
