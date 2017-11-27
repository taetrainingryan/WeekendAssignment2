//package com.example.ryan.weekendassignment2;
//
///**
// * Created by Ryan on 25/11/2017.
// */
//
//import android.content.Context;
//import android.net.Uri;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
//
//import java.util.List;package com.example.ryan.weekendassignment2;
//
//import android.content.Context;
//import android.net.Uri;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.ryan.weekendassignment2.data.network.model.TrackDetails;
//
//import java.util.List;
//
///**
// * Created by Ryan on 24/11/2017.
// */
//
//public class adapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>{
//
//    private List<TrackDetails> trackDetails;
//    private int row_tracks;
//    private Context applicationContext;
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(applicationContext).inflate(row_tracks, parent, false);
//        return new MyViewHolder(view);
//    }
//
//
//    public adapter(List<TrackDetails> trackDetails, int row_tracks, Context applicationContext) {
//        this.trackDetails = trackDetails;
//        this.row_tracks = row_tracks;
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewAdapter.MyViewHolder holder, int position) {
//
//        holder.trackName.setText(trackDetails.get(position).getCollectionName());
//        holder.price.setText("£" + trackDetails.get(position).getTrackPrice().toString());
//        holder.artistName.setText(trackDetails.get(position).getArtistName());
//        Uri uri = Uri.parse(trackDetails.get(position).getArtworkUrl60());
//        holder.photo.setImageURI(uri);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return trackDetails.size();
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView trackName, price, artistName;
//        ImageView photo;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            trackName = (TextView) itemView.findViewById(R.id.tvTrackName);
//            artistName = (TextView) itemView.findViewById(R.id.tvArtistName);
//            price = (TextView) itemView.findViewById(R.id.tvPrice);
//            photo = (ImageView) itemView.findViewById(R.id.ivPhoto);
//
//
//
//        }
//    }
//}
