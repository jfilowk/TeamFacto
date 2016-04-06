package com.jfilowk.teamfactory.internal.di.module;

import android.content.Context;
import com.jfilowk.teamfactory.datasource.binder.EventMapper;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.binder.TeamMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.EventCacheImpl;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDB;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDBImpl;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDB;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDBImpl;
import com.jfilowk.teamfactory.datasource.cache.helper.TeamDB;
import com.jfilowk.teamfactory.datasource.cache.helper.TeamDBImpl;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  private TeamFactoApp teamFactoApp;

  public ApplicationModule(TeamFactoApp teamFactoApp) {

    this.teamFactoApp = teamFactoApp;
  }

  @Provides Context provideApplicationContext() {
    return this.teamFactoApp.getApplicationContext();
  }

  @Provides @Singleton RandomUserMapper provideRandomUserMapper(){
    return new RandomUserMapper();
  }

  @Provides @Singleton TeamMapper provideTeamMapper(){
    return new TeamMapper();
  }

  @Provides @Singleton EventMapper provideEventMapper(){
    return new EventMapper();
  }

  @Provides @Singleton RandomUserDB provideRandomUserDB(RandomUserDBImpl randomUserDB){
    return randomUserDB;
  }

  @Provides @Singleton TeamDB provideTeamDB(TeamDBImpl teamDB) {
    return teamDB;
  }

  @Provides @Singleton EventDB provideEventDB(EventDBImpl eventDB) {
    return eventDB;
  }

  @Provides @Singleton EventCache provideEventCache(EventCacheImpl eventCache){
    return eventCache;
  }

}
