package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import timber.log.Timber;

/**
 * Created by Javi on 30/09/14.
 */
public class CreateEventJob extends Job {

  private Event event;
  private EventCallbackBase listener;
  private EventCache cache;

  public CreateEventJob(Event event, EventCache cache, EventCallbackBase listener) {
    super(new Params(Priority.MID));
    this.event = event;
    this.listener = listener;
    this.cache = cache;
  }

  @Override public void onAdded() {
  }

  @Override public void onRun() throws Throwable {

    Timber.e("Entro en el onRun");
    boolean result = cache.createEvent(event);

    if (result) {
      if (listener != null) listener.onSuccess();
    } else {
      Timber.e("Error en el onRun");
      listener.onError();
    }
  }

  @Override protected void onCancel() {
    listener.onError();
  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return false;
  }
}
