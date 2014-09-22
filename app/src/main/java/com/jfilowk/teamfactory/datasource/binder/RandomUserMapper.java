package com.jfilowk.teamfactory.datasource.binder;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.terro.entities.Result;
import com.terro.entities.UserRandomResponse;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUserMapper {

    public RandomUser transformResultToRandomUser (Result result) {

        RandomUser randomUser = new RandomUser();

        randomUser.setName(result.getUser().getName().getFirst()+" "+result.getUser().getName().getLast());
        randomUser.setGender(result.getUser().getGender());
        randomUser.setPicture(result.getUser().getPicture());
        randomUser.setSeed(result.getSeed());

        return randomUser;
    }

    public RandomUserCollection transformResultToRandomUserCollection (UserRandomResponse response){
        RandomUserCollection collection = new RandomUserCollection();

        for (Result result : response.getResults()) {
            RandomUser user = transformResultToRandomUser(result);
            collection.add(user);
        }

        return collection;
    }

}
