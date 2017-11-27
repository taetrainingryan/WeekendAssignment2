package com.example.ryan.weekendassignment2.data.network.service;

/**
 * Created by Ryan on 24/11/2017.
 */

public class API_List {

    //https://itunes.apple.com/search?term=classic&amp;media=music&amp;entity=song&amp;limit=50

    public static final String BASE_URL = "https://itunes.apple.com/";
    //public static final String SEARCH = "search";
    public static final String CLASSIC_URL = ("search");
    public static final String ROCK_URL = "term=rock";
    public static final String POP_URL = "term=pop";
    public static final String TAIL_URL = "&amp;media=music&amp;entity=song&amp;limit=50";

}
