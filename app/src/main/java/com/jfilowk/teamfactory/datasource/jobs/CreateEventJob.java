package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by Javi on 30/09/14.
 */
public class CreateEventJob extends Job {

    Event event;
    EventDataSource eventDataSource;

    public CreateEventJob(Event event) {
        super(new Params(Priority.MID).requireNetwork());
        this.event = event;
        eventDataSource = new EventDataSourceImpl();
    }

    @Override
    public void onAdded() {


    }

    @Override
    public void onRun() throws Throwable {

        eventDataSource.createEvent(event,new EventCallbackBase() {
    @Override
    public void onSuccess() {
        System.out.println("I like the way you do it.");
    }

    @Override
    public void onError() {

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
