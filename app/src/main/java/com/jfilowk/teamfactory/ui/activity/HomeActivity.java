package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.ui.fragments.DialogSelectTeam;
import com.jfilowk.teamfactory.ui.fragments.FragmentInitProgress;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenter;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenterImp;
import com.jfilowk.teamfactory.ui.views.HomeActivityView;

public class HomeActivity extends ActionBarActivity implements HomeActivityView {

    private HomeActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.create_team) {
            // Mostrar la creación del equipo.
            presenter.selectTeam();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initProgressFragment() {

        FragmentInitProgress progress = new FragmentInitProgress();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, progress).commit();

    }

    @Override
    public void initMainFragment(Object object) {

    }

    private void init() {
        this.presenter = new HomeActivityPresenterImp(this);
    }

    @Override
    public void initSelectTeamFragment() {
        showSelectTeamFragment();
    }

    private void showSelectTeamFragment(){

        DialogSelectTeam dialogSelectTeam = new DialogSelectTeam();
        dialogSelectTeam.show(getSupportFragmentManager(), "fragment_select_team");

    }


}
