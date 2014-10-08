package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by Javi on 02/10/14.
 */
public class GetEventsJob extends Job{

    private EventCache cache;
    private EventCallback listener;

    public GetEventsJob(EventCache eventCache, EventCallback callback) {
        super(new Params(Priority.MID));
        this.cache = eventCache;
        this.listener = callback;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        this.cache.getEvents(new EventCacheCallback() {
            @Override
            public void onSuccess(EventCollection eventCollection) {
                listener.onSuccess(eventCollection);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
