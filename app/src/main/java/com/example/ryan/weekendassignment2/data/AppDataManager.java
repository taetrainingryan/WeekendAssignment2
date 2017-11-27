package com.example.ryan.weekendassignment2.data;

import com.example.ryan.weekendassignment2.data.network.ApiHelper;
import com.example.ryan.weekendassignment2.data.network.AppApiHelper;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;

import io.reactivex.Observable;

/**
 * Created by Ryan on 26/11/2017.
 */

public class AppDataManager implements IDataManager {

    private ApiHelper apiHelper;

    public AppDataManager() {

        apiHelper = new AppApiHelper();
    }

    @Override
    public Observable<TrackModel> getTrackModel(String classic) {
        return apiHelper.getTrackModel(classic);
    }



}
