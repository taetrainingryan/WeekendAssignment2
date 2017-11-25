package com.example.ryan.weekendassignment2.services;

import com.example.ryan.weekendassignment2.model.TrackModel;
import com.example.ryan.weekendassignment2.util.API_List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryan on 24/11/2017.
 */

public interface RequestInterface {

    @GET(API_List.CLASSIC_URL)
    Observable<TrackModel>getTrackModel(@Query("term") String classic);

//    @GET(API_List.ROCK_URL)
//    Observable<TrackModel>getTrackModel(@Query("term") String rock);
//
//    @GET(API_List.POP_URL)
//    Observable<TrackModel>getTrackModel(@Query("term") String pop);


}
