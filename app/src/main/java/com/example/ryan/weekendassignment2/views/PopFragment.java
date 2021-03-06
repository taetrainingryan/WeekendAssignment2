package com.example.ryan.weekendassignment2.views;


import android.os.Bundle;
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

import com.example.ryan.weekendassignment2.R;
import com.example.ryan.weekendassignment2.ViewAdapter;
import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.data.network.service.RequestInterface;
import com.example.ryan.weekendassignment2.services.ServerConnection;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {

    RequestInterface requestInterface;
    TrackDetails trackDetails;
    TextView trackTitle;
    private List<TrackDetails> results;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    public PopFragment() {
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
        return inflater.inflate(R.layout.fragment_pop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRetainInstance(true);

        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefreshpop);
        requestInterface = ServerConnection.getServerConnection();

        checkNetwork();

    }

    public void initializeRecyclerView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvPopTracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter = new ViewAdapter(getActivity(), results);

        recyclerView.setAdapter(viewAdapter);
    }

    public void checkNetwork(){

        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {

                        if (isConnectedToInternet == true){

                            pingApi();
                            initializeSwipeListener();
                        }

                        else{

                            Toast.makeText(getContext(), "No internet connection detected.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void pingApi(){
        requestInterface.getTrackModel("pop")
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

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        initializeSwipeListener();

                    }
                });
    }

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
}

