package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.internal.di.component.ActivityComponent;
import com.jfilowk.teamfactory.internal.di.component.DaggerActivityComponent;
import com.jfilowk.teamfactory.internal.di.module.ActivityModule;
import com.jfilowk.teamfactory.ui.fragments.FragmentError;
import com.jfilowk.teamfactory.ui.fragments.FragmentGenerateTeam;
import com.jfilowk.teamfactory.ui.fragments.FragmentInitProgress;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenter;
import javax.inject.Inject;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeamActivity extends BaseActivity
    implements GenerateTeamPresenter.GenerateTeamView {

  @Inject GenerateTeamPresenter presenter;
  private Event event;
  private static String KEY_EVENT = "event";
  private ActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_generate_team);
    init();
    initializeInjector();
    presenter.attachView(this);
    presenter.onResume(this.event);
  }

  private void initializeInjector() {
    component = DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(new ActivityModule(this))
        .build();
    component.inject(this);
  }

  @Override public void initProgressFragment() {
    FragmentInitProgress progressFragment = new FragmentInitProgress();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container_generate, progressFragment)
        .commit();
  }

  @Override public void initMainFragment(Event event) {
    FragmentGenerateTeam fragmentGenerateTeam = FragmentGenerateTeam.newInstance(event);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container_generate, fragmentGenerateTeam)
        .commit();
  }

  @Override public void initErrorFragment() {
    FragmentError fragmentError = FragmentError.newInstance();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container_generate, fragmentError)
        .commit();
    Toast.makeText(getApplicationContext(), "Error, check your Internet connection",
        Toast.LENGTH_SHORT).show();
  }

  public void init() {
    this.event = (Event) getIntent().getSerializableExtra(KEY_EVENT);
    ButterKnife.inject(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
