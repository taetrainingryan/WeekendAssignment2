package com.example.ryan.weekendassignment2.views.classic;

import android.os.SystemClock;
import android.widget.Toast;

import com.example.ryan.weekendassignment2.data.IDataManager;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.views.ui.base.BasePresenter;
import com.example.ryan.weekendassignment2.views.ui.base.MvpPresenter;
import com.example.ryan.weekendassignment2.views.ui.base.MvpView;
import com.example.ryan.weekendassignment2.views.ui.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ryan on 26/11/2017.
 */

public class ClassicListPresenter<V extends IClassicListMvpView> extends BasePresenter<V>
implements IClassicListMvpPresenter<V> {

    public ClassicListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewPrepared() {


                getDataManager().getTrackModel("classic")
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Observer<TrackModel>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TrackModel trackModel) {

                                List results = trackModel.getResults();

                                getMvpView().onFetchDataSuccess(results);

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
