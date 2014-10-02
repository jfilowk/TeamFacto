package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by Javi on 02/10/14.
 */
public class GetEventJob extends Job {

    private Event event;
    private AnEventCacheCallback listener;
    private EventCache cache;

    public GetEventJob(Event event, EventCache cache, AnEventCacheCallback listener) {
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
       this.cache.getEvent(event, new AnEventCacheCallback() {
            @Override
            public void onSuccess(Event event) {
                listener.onSuccess(event);
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
