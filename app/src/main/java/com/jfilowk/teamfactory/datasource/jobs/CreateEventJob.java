package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by Javi on 30/09/14.
 */
public class CreateEventJob extends Job {

    private Event event;
    private EventCallbackBase listener;
    private EventCache cache;

    public CreateEventJob(Event event, EventCache cache, EventCallbackBase listener) {
        super(new Params(Priority.MID).requireNetwork());
        this.event = event;
        this.listener = listener;
        this.cache = cache;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {

        boolean result = cache.createEvent(event);

        if (result) {
            if (listener != null)
                listener.onSuccess();
        } else {
            listener.onError();
        }
    }

    @Override
    protected void onCancel() {
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
