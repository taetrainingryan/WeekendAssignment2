package com.example.ryan.weekendassignment2.views;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.weekendassignment2.MainActivity;
import com.example.ryan.weekendassignment2.R;
import com.example.ryan.weekendassignment2.ViewAdapter;
import com.example.ryan.weekendassignment2.data.AppDataManager;
import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.model.realm.RealmController;
import com.example.ryan.weekendassignment2.data.network.service.RequestInterface;
import com.example.ryan.weekendassignment2.services.ServerConnection;
import com.example.ryan.weekendassignment2.views.classic.ClassicListPresenter;
import com.example.ryan.weekendassignment2.views.classic.IClassicListMvpView;
import com.example.ryan.weekendassignment2.views.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicFragment extends Fragment implements IClassicListMvpView {

    RequestInterface requestInterface;
    TrackDetails trackDetails;
    TextView trackTitle;
    private List<TrackDetails> results;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    long apiPinged;
    SwipeRefreshLayout swipeRefreshLayout;
    private ClassicListPresenter classicListPresenter;

    public ClassicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        requestInterface = ServerConnection.getServerConnection();
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);

        apiPinged = SystemClock.elapsedRealtime();

        Realm.init(getContext());
        initializeRealm();

//        initializeRecyclerView();
//        initializePresenter();
//        getData();
//        initializeSwipeListener();]

        checkNetwork();

    }

    private void getData() {


        Toast.makeText(getContext(), "Api Pinged", Toast.LENGTH_SHORT).show();
        classicListPresenter.onViewPrepared();
    }

    public void initializePresenter(){

        classicListPresenter = new ClassicListPresenter(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        classicListPresenter.onAttach(this);

    }


    public void initializeRecyclerView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvClassicTracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    public void initializeRealm(){
        RealmController.getInstance();
    }

    public boolean timeExpired(double time){

        long timeNow = SystemClock.elapsedRealtime();

        //5 minutes
        if (time > (timeNow + 30) ){

            return true;
        }

        else {

            return false;
        }
    }



//    public void makeApiRequest(){
//
//        if (timeExpired(apiPinged) || results == null){
//
//            requestInterface.getTrackModel("classic")
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Observer<TrackModel>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(TrackModel trackModel) {
//
//                            results = new ArrayList<>(trackModel.getResults());
//
//                            initializeRecyclerView();
//
//                            apiPinged = SystemClock.elapsedRealtime();
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                            initializeSwipeListener();
//
//
//                        }
//
//                    });
//
//
//            Toast.makeText(getContext(), "Api Pinged", Toast.LENGTH_SHORT).show();
//
//        }
//
//        else{
//
//            results = RealmController.getInstance().getTracks();
//
//            Toast.makeText(getContext(), "results size: " + String.valueOf(results.size()), Toast.LENGTH_SHORT).show();
//
//            initializeRecyclerView();
//
//            //Toast.makeText(getContext(), "Database pinged", Toast.LENGTH_SHORT).show();
//
//
//        }


//    }

        public void initializeSwipeListener(){
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.

                        viewAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }
        );
    }

//    public void saveToDatabase(List trackDetails){
//
//        RealmTrackList realmTrackList = new RealmTrackList(trackDetails);
//        RealmController.getInstance().saveTracks(realmTrackList);
//
//    }

    public void checkNetwork(){

        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {

                        if (isConnectedToInternet == true){


                            initializeRecyclerView();
                            initializePresenter();
                            getData();
                            initializeSwipeListener();
                        }

                        else{

                            Toast.makeText(getContext(), "no internet connection detected", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    @Override
    public void onFetchDataSuccess(List<TrackDetails> TrackDetails) {

            results = TrackDetails;

        viewAdapter = new ViewAdapter(getActivity(), results);

        recyclerView.setAdapter(viewAdapter);

    }

    @Override
    public void onFetchDataError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
