package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.fragments.FragmentInitProgress;
import com.jfilowk.teamfactory.ui.fragments.Generate;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenterImpl;
import com.jfilowk.teamfactory.ui.views.GenerateTeamView;

import butterknife.ButterKnife;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeam extends ActionBarActivity implements GenerateTeamView {

    GenerateTeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_team);
        init();
        presenter.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*int id = item.getItemId();
        if (id == R.id.create_team) {
            // Mostrar la creación del equipo.
            presenter.selectTeam();
            return true;
        }
        return super.onOptionsItemSelected(item);*/
        return true;
    }

    @Override
    public void initProgressFragment() {
        FragmentInitProgress progressFragment = new FragmentInitProgress();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_generate, progressFragment).commit();
    }

    @Override
    public void initMainFragment(EventCollection event) {

        Generate generate = new Generate();
        Bundle b = new Bundle();
        b.putString("Hola", event.get(0).getType());
        generate.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_generate, generate).commit();

    }

    @Override
    public void initErrorFragment() {

    }

    public void init(){

        presenter = new GenerateTeamPresenterImpl(this);
        ButterKnife.inject(this);

    }
}
