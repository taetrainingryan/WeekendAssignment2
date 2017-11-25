package com.example.ryan.weekendassignment2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ryan on 24/11/2017.
 */

public class TrackModel {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<TrackDetails> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<TrackDetails> getResults() {
        return results;
    }

    public void setResults(List<TrackDetails> results) {
        this.results = results;
    }

}