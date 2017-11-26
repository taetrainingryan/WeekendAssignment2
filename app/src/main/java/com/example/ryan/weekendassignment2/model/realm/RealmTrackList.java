package com.example.ryan.weekendassignment2.model.realm;

import com.example.ryan.weekendassignment2.model.TrackDetails;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Ryan on 26/11/2017.
 */

public class RealmTrackList extends RealmObject {

    private RealmList<TrackDetails> trackDetails;

    public RealmTrackList() {
    }

    public RealmTrackList(List TrackDetails) {
        this.trackDetails = trackDetails;
    }

    public RealmList<TrackDetails> getTrackDetails() {
        return trackDetails;
    }

    public void setTrackDetails(RealmList<TrackDetails> trackDetails) {
        this.trackDetails = trackDetails;
    }
}
