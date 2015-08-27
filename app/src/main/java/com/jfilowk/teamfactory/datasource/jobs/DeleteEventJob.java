package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import timber.log.Timber;

/**
 * Created by Javi on 14/10/14.
 */
public class DeleteEventJob extends Job {

    private long id;
    private EventCallbackBase listener;
    private EventCache cache;

    public DeleteEventJob(long id, EventCache cache, EventCallbackBase listener) {
        super(new Params(Priority.MID));
        this.id = id;
        this.listener = listener;
        this.cache = cache;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        Timber.e("Entro en el onRun");
        boolean result = cache.deleteEvent(id);

        if (result) {
            if (listener != null)
                listener.onSuccess();
        } else {
            Timber.e("Error en el onRun");
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
