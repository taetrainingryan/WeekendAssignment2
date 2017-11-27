package com.example.ryan.weekendassignment2.views.delegates;

/**
 * Created by Ryan on 25/11/2017.
 */

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.weekendassignment2.R;
import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

/**
 * Created by Ryan on 25/11/2017.
 */

public class RockAdapterDelegate extends AdapterDelegate<List<TrackDetails>> {

    private LayoutInflater layoutInflater;

    public RockAdapterDelegate(Activity activity) {
        layoutInflater = activity.getLayoutInflater();
    }


    @Override
    protected boolean isForViewType(@NonNull List<TrackDetails> items, int position) {
        return items.get(position) instanceof TrackDetails;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.row, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<TrackDetails> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        MyViewHolder vh = (MyViewHolder) holder;

        ((MyViewHolder) holder).trackName.setText(items.get(position).getCollectionName());
        ((MyViewHolder) holder).price.setText("£" + items.get(position).getTrackPrice().toString());
        ((MyViewHolder) holder).artistName.setText(items.get(position).getArtistName());
        Uri uri = Uri.parse(items.get(position).getArtworkUrl60());
        ((MyViewHolder) holder).photo.setImageURI(uri);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView trackName, price, artistName;
        ImageView photo;

        public MyViewHolder(View itemView) {
            super(itemView);

            trackName = (TextView) itemView.findViewById(R.id.tvTrackName);
            artistName = (TextView) itemView.findViewById(R.id.tvArtistName);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
            photo = (ImageView) itemView.findViewById(R.id.ivPhoto);



        }
    }
}

