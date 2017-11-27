package com.example.ryan.weekendassignment2.views.classic;

import com.example.ryan.weekendassignment2.views.ui.base.MvpPresenter;

/**
 * Created by Ryan on 26/11/2017.
 */

public interface IClassicListMvpPresenter <V extends IClassicListMvpView> extends MvpPresenter<V>{

    //void onCallClassicList();
    void onViewPrepared();
}
