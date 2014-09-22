package com.jfilowk.teamfactory.datasource.binder;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.terro.entities.Result;
import com.terro.entities.UserRandomResponse;

import java.util.List;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUserMapper {

    public RandomUser transformResultToRandomUser(Result result) {

        RandomUser randomUser = new RandomUser();

        randomUser.setSeed(result.getSeed());
        randomUser.setName(result.getUser().getName().getFirst() + " " + result.getUser().getName().getLast());
        randomUser.setGender(result.getUser().getGender());
        randomUser.setPicture(result.getUser().getPicture());

        return randomUser;
    }

    public RandomUserCollection transformResultToRandomUserCollection(UserRandomResponse response) {
        RandomUserCollection collection = new RandomUserCollection();

        for (Result result : response.getResults()) {
            RandomUser user = transformResultToRandomUser(result);
            collection.add(user);
        }

        return collection;
    }

    public RandomUserCollection transformCacheToRandomUserCollection(List<RandomUser> list) {
        RandomUserCollection collection = new RandomUserCollection();
        for (RandomUser user : list) {
            collection.add(user);
        }
        return collection;
    }

}
