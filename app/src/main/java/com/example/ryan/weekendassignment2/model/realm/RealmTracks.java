package com.example.ryan.weekendassignment2.model.realm;

import io.realm.RealmObject;

/**
 * Created by Ryan on 25/11/2017.
 */

public class RealmTracks extends RealmObject{

    String trackName, artistName, trackPrice, trackImage;

    public RealmTracks() {
    }

    public RealmTracks(String trackName, String artistName, String trackPrice, String trackImage) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.trackPrice = trackPrice;
        this.trackImage = trackImage;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getTrackImage() {
        return trackImage;
    }

    public void setTrackImage(String trackImage) {
        this.trackImage = trackImage;
    }
}
