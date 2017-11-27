package com.example.ryan.weekendassignment2.views.rock;

import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.views.ui.base.MvpView;

import java.util.List;

/**
 * Created by Ryan on 27/11/2017.
 */

public interface IRockListMvpView extends MvpView {

    void onFetchDataSuccess(List<TrackDetails> TrackDetails);
    void onFetchDataError(String message);
}
