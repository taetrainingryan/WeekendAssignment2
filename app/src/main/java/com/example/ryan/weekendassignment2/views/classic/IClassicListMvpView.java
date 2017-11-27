package com.example.ryan.weekendassignment2.views.classic;

import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.views.ui.base.MvpView;

import java.util.List;

/**
 * Created by Ryan on 26/11/2017.
 */

public interface IClassicListMvpView extends MvpView {

    void onFetchDataSuccess(List<TrackDetails> TrackDetails);
    void onFetchDataError(String message);
}
