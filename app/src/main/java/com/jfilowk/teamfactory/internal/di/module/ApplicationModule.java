package com.jfilowk.teamfactory.internal.di.module;

import android.content.Context;
import android.util.Log;
import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
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
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import com.terro.RandomUser;
import com.terro.services.UserServiceAsync;
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

  @Provides @Singleton RandomUserMapper provideRandomUserMapper() {
    return new RandomUserMapper();
  }

  @Provides @Singleton TeamMapper provideTeamMapper() {
    return new TeamMapper();
  }

  @Provides @Singleton EventMapper provideEventMapper() {
    return new EventMapper();
  }

  @Provides @Singleton RandomUserDB provideRandomUserDB(RandomUserDBImpl randomUserDB) {
    return randomUserDB;
  }

  @Provides @Singleton TeamDB provideTeamDB(TeamDBImpl teamDB) {
    return teamDB;
  }

  @Provides @Singleton EventDB provideEventDB(EventDBImpl eventDB) {
    return eventDB;
  }

  @Provides @Singleton EventCache provideEventCache(EventCacheImpl eventCache) {
    return eventCache;
  }

  @Provides @Singleton UserServiceAsync provideUserServiceAsync() {
    return new RandomUser().setIsDebug(false).userServicesAsync();
  }

  @Provides @Singleton RandomUserApi provideRandomUserApi(RandomUserApiImpl randomUserApi) {
    return randomUserApi;
  }

  @Provides @Singleton EventDataSource provideEventDataSource(EventDataSourceImpl eventDataSource) {
    return eventDataSource;
  }

  @Provides @Singleton JobManager provideJobManager() {
    Configuration configuration =
        new Configuration.Builder(provideApplicationContext()).customLogger(new CustomLogger() {
          private static final String TAG = "JOBS";

          @Override public boolean isDebugEnabled() {

            return true;
          }

          @Override public void d(String text, Object... args) {
            Log.d(TAG, String.format(text, args));
          }

          @Override public void e(Throwable t, String text, Object... args) {
            Log.e(TAG, String.format(text, args), t);
          }

          @Override public void e(String text, Object... args) {
            Log.e(TAG, String.format(text, args));
          }
        }).minConsumerCount(1).maxConsumerCount(3).loadFactor(3).consumerKeepAlive(120).build();
    return new JobManager(provideApplicationContext(), configuration);
  }
}
