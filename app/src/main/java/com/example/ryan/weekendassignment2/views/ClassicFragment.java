package com.example.ryan.weekendassignment2.views;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.weekendassignment2.R;
import com.example.ryan.weekendassignment2.ViewAdapter;
import com.example.ryan.weekendassignment2.model.TrackDetails;
import com.example.ryan.weekendassignment2.model.TrackModel;
import com.example.ryan.weekendassignment2.model.realm.RealmController;
import com.example.ryan.weekendassignment2.model.realm.RealmTrackList;
import com.example.ryan.weekendassignment2.model.realm.RealmTracks;
import com.example.ryan.weekendassignment2.services.RequestInterface;
import com.example.ryan.weekendassignment2.services.ServerConnection;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicFragment extends Fragment {

    RequestInterface requestInterface;
    TrackDetails trackDetails;
    TextView trackTitle;
    private List<TrackDetails> results;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    long apiPinged;
    SwipeRefreshLayout swipeRefreshLayout;



    public ClassicFragment() {
        // Required empty public constructor
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

        makeApiRequest();
    }


    public void initializeRecyclerView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvClassicTracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter = new ViewAdapter(getActivity(), results);

        recyclerView.setAdapter(viewAdapter);
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

    public void makeApiRequest(){

        if (timeExpired(apiPinged) || results == null){

            requestInterface.getTrackModel("classic")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<TrackModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(TrackModel trackModel) {

                            results = new ArrayList<>(trackModel.getResults());

                            initializeRecyclerView();



                            apiPinged = SystemClock.elapsedRealtime();

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                            initializeSwipeListener();


                        }

                    });


            Toast.makeText(getContext(), "Api Pinged", Toast.LENGTH_SHORT).show();

        }

        else{

            results = RealmController.getInstance().getTracks();

            Toast.makeText(getContext(), "results size: " + String.valueOf(results.size()), Toast.LENGTH_SHORT).show();

            initializeRecyclerView();

            //Toast.makeText(getContext(), "Database pinged", Toast.LENGTH_SHORT).show();


        }


    }

        public void initializeSwipeListener(){
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.

                        makeApiRequest();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }
        );
    }

    public void saveToDatabase(List trackDetails){

        RealmTrackList realmTrackList = new RealmTrackList(trackDetails);
        RealmController.getInstance().saveTracks(realmTrackList);

    }


}
