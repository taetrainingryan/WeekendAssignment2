package com.example.ryan.weekendassignment2.data.network;

import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.data.network.service.RequestInterface;
import com.example.ryan.weekendassignment2.data.network.service.ServerConnection;

import io.reactivex.Observable;

/**
 * Created by Ryan on 26/11/2017.
 */

public class AppApiHelper implements ApiHelper {

    public RequestInterface requestInterface;

    public AppApiHelper() {

        requestInterface = ServerConnection.getServerConnection();
    }

    @Override
    public Observable<TrackModel> getTrackModel(String classic) {
        return requestInterface.getTrackModel("classic");
    }
}
