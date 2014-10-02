package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;
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
       this.event = (Event) getIntent().getSerializableExtra(KEY_EVENT);
        init();
        presenter.onResume(this.event);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.generate_team_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create_team) {
            // Mostrar la creación del equipo.
            Toast.makeText(this, "Holita111", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

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

    }

    public void init() {
        presenter = new GenerateTeamPresenterImpl(this);
        ButterKnife.inject(this);
    }
}
