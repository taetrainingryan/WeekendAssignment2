package com.example.ryan.weekendassignment2.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.weekendassignment2.R;
import com.example.ryan.weekendassignment2.ViewAdapter;
import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.example.ryan.weekendassignment2.data.network.model.TrackModel;
import com.example.ryan.weekendassignment2.data.network.service.RequestInterface;
import com.example.ryan.weekendassignment2.services.ServerConnection;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RockFragment extends Fragment {

    RequestInterface requestInterface;
    TrackDetails trackDetails;
    TextView trackTitle;
    private List<TrackDetails> results;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;


    public RockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rock, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestInterface = ServerConnection.getServerConnection();

        requestInterface.getTrackModel("rock")
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

                    }
                });
    }

    public void initializeRecyclerView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvRockTracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter = new ViewAdapter(getActivity(), results);

        recyclerView.setAdapter(viewAdapter);
    }
}
