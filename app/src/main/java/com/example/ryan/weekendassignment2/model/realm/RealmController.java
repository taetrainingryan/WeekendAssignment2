package com.example.ryan.weekendassignment2.model.realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ryan on 25/11/2017.
 */

public class RealmController {

    private static RealmController instance = null;

    public static RealmController getInstance(){

        synchronized (RealmController.class){

            if(instance == null){

                synchronized (RealmController.class){

                    instance = new RealmController(Realm.getDefaultInstance());

                }
            }
        }

        return instance;
    }

    private Realm realm;

    public RealmController(Realm realm) {

        this.realm = realm;
    }

    public void saveCustomer(final RealmTracks realmTracks){

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(realmTracks);

            }
        });
    }

    /*
    Returns list of all customers.
     */

    public ArrayList<RealmTracks> getTracksList(){

        ArrayList<RealmTracks> tracks = new ArrayList<>();

        RealmResults<RealmTracks> realmTracksRealmResults = realm.where(RealmTracks.class).findAll();

        for(RealmTracks realmTracks: realmTracksRealmResults){
            tracks.add(realmTracks);
        }

        return tracks;

    }
}
