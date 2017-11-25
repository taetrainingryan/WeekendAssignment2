package com.example.ryan.weekendassignment2.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.ryan.weekendassignment2.services.RequestInterface;
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
public class ClassicFragment extends Fragment {

    RequestInterface requestInterface;
    TrackDetails trackDetails;
    TextView trackTitle;
    private List<TrackDetails> results;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;


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

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvClassicTracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter = new ViewAdapter(getActivity(), results);

        recyclerView.setAdapter(viewAdapter);
    }
}
