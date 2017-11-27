package com.example.ryan.weekendassignment2.views.rock;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ryan.weekendassignment2.data.IDataManager;
import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.views.ui.base.BasePresenter;
import com.example.ryan.weekendassignment2.views.ui.base.MvpView;
import com.example.ryan.weekendassignment2.views.ui.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ryan on 27/11/2017.
 */

public class RockListPresenter<V extends IRockListMvpView> extends BasePresenter<V>
        implements  IRockListMvpPresenter<V>{

    public RockListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getDataManager().getTrackModel("rock")
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Observer<TrackModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TrackModel trackModel) {

                        List <TrackDetails> rockResults = trackModel.getResults();
                        getMvpView().onFetchDataSuccess(rockResults);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
