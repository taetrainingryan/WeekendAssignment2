package com.example.ryan.weekendassignment2.views.delegates;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

import java.io.IOException;
import java.util.List;

/**
 * Created by Ryan on 25/11/2017.
 */

public class ClassicAdapterDelegate extends AdapterDelegate <List<TrackDetails>> {

    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity1;
    MediaPlayer mPlayer = new MediaPlayer();


    public ClassicAdapterDelegate(Activity activity) {
        layoutInflater = activity.getLayoutInflater();

        activity1 = activity;
        context = activity.getApplicationContext();
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
    protected void onBindViewHolder(@NonNull final List<TrackDetails> items, final int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        MyViewHolder vh = (MyViewHolder) holder;

        ((MyViewHolder) holder).trackName.setText(items.get(position).getCollectionName());
        ((MyViewHolder) holder).price.setText("£" + items.get(position).getTrackPrice().toString());
        ((MyViewHolder) holder).artistName.setText(items.get(position).getArtistName());
        Uri uri = Uri.parse(items.get(position).getArtworkUrl60());
        ((MyViewHolder) holder).photo.setImageURI(uri);

        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaPlayer mediaPlayer = new MediaPlayer();


                if (mediaPlayer.isPlaying()) {

                    mediaPlayer.stop();

                }

                else if (!mediaPlayer.isPlaying()){

                    String url = items.get(position).getPreviewUrl(); // your URL here

                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    try {
                        mediaPlayer.setDataSource(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }

                //Toast.makeText(context, "Item clicked: " + items.get(position).getArtistName(), Toast.LENGTH_SHORT).show();
            }
        });

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

