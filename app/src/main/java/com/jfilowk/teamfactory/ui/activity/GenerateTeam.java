package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.fragments.FragmentError;
import com.jfilowk.teamfactory.ui.fragments.FragmentGenerateTeam;
import com.jfilowk.teamfactory.ui.fragments.FragmentInitProgress;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenterImpl;
import com.jfilowk.teamfactory.ui.views.GenerateTeamView;

import butterknife.ButterKnife;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeam extends ActionBarActivity implements GenerateTeamView {

    private GenerateTeamPresenter presenter;
    private Event event;
    private static String KEY_EVENT = "event";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_team);
        init();
        presenter.onResume(this.event);

    }

    @Override
    public void initProgressFragment() {
        FragmentInitProgress progressFragment = new FragmentInitProgress();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_generate, progressFragment).commit();
    }

    @Override
    public void initMainFragment(Event event) {
        FragmentGenerateTeam fragmentGenerateTeam = FragmentGenerateTeam.newInstance(event);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_generate, fragmentGenerateTeam).commit();
    }

    @Override
    public void initErrorFragment() {
        FragmentError fragmentError = FragmentError.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_generate, fragmentError).commit();
        Toast.makeText(getApplicationContext(), "Error, check your Internet connection", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void init() {
        this.event = (Event) getIntent().getSerializableExtra(KEY_EVENT);
        presenter = new GenerateTeamPresenterImpl(this);
        ButterKnife.inject(this);
    }
}
