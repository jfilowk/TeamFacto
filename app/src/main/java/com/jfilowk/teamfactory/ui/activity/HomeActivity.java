package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.fragments.DialogSelectTeam;
import com.jfilowk.teamfactory.ui.fragments.FragmentError;
import com.jfilowk.teamfactory.ui.fragments.FragmentInitProgress;
import com.jfilowk.teamfactory.ui.fragments.FragmentListAllEvents;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenter;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenterImpl;

public class HomeActivity extends ActionBarActivity
    implements HomeActivityPresenter.HomeActivityView {

  private HomeActivityPresenter presenter;

  private static String FRAGMENT_SELECT_TEAM = "fragment_select_team";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    presenter.attachView(this);
    init();
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();
    if (id == R.id.create_team) {

      presenter.selectTeam();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void initProgressFragment() {

    FragmentInitProgress progress = new FragmentInitProgress();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, progress)
        .commit();
  }

  @Override public void initMainFragment(EventCollection eventCollection) {
    FragmentListAllEvents fragmentListAllEvents =
        FragmentListAllEvents.newInstance(eventCollection);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, fragmentListAllEvents)
        .commit();
  }

  private void init() {
    this.presenter = new HomeActivityPresenterImpl(this);
  }

  @Override public void initSelectTeamFragment() {
    showSelectTeamFragment();
  }

  private void showSelectTeamFragment() {

    DialogSelectTeam dialogSelectTeam = DialogSelectTeam.newInstance();
    dialogSelectTeam.show(getSupportFragmentManager(), FRAGMENT_SELECT_TEAM);
  }

  @Override public void initErrorFragment() {
    FragmentError fragmentError = FragmentError.newInstance();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, fragmentError)
        .commit();
  }
}
