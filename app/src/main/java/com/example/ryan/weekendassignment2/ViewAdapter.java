package com.example.ryan.weekendassignment2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.weekendassignment2.model.TrackDetails;
import com.example.ryan.weekendassignment2.views.delegates.ClassicAdapterDelegate;
import com.example.ryan.weekendassignment2.views.delegates.PopAdapterDelegate;
import com.example.ryan.weekendassignment2.views.delegates.RockAdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

/**
 * Created by Ryan on 24/11/2017.
 */

    public class ViewAdapter extends RecyclerView.Adapter {

        private AdapterDelegatesManager<List<TrackDetails>> delegatesManager;
        private List<TrackDetails> items;

        public ViewAdapter(Activity activity, List<TrackDetails> items) {
            this.items = items;

            delegatesManager = new AdapterDelegatesManager<>();

            // AdapterDelegatesManager internally assigns view types integers
            delegatesManager.addDelegate(new ClassicAdapterDelegate(activity))
                    .addDelegate(new PopAdapterDelegate(activity))
                    .addDelegate(new RockAdapterDelegate(activity));

            // You can explicitly assign integer view type if you want to
            //delegatesManager.addDelegate(23, new SnakeAdapterDelegate(activity));
        }

        @Override
        public int getItemViewType(int position) {
            return delegatesManager.getItemViewType(items, position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return delegatesManager.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            delegatesManager.onBindViewHolder(items, position, holder);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
