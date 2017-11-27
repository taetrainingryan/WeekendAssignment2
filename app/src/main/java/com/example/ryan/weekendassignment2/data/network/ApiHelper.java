package com.example.ryan.weekendassignment2.data.network;

import com.example.ryan.weekendassignment2.data.network.model.TrackModel;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Ryan on 26/11/2017.
 */

public interface ApiHelper {

    Observable<TrackModel> getTrackModel(@Query("term") String classic);
}
