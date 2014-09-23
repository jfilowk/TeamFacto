package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventCache {
    public void getEventCache (EventCacheCallback callback);
}
