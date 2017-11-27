package com.example.ryan.weekendassignment2.views.rock;

import com.example.ryan.weekendassignment2.views.ui.base.MvpPresenter;

/**
 * Created by Ryan on 27/11/2017.
 */

public interface IRockListMvpPresenter <V extends IRockListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}
