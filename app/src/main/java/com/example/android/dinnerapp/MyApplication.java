package com.example.android.dinnerapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by irahavoi on 2016-01-10.
 */
public class MyApplication extends Application{
    public Tracker mTracker;
    public void startTracking(){
        if(mTracker == null){
            GoogleAnalytics ga = GoogleAnalytics.getInstance(this);

            mTracker = ga.newTracker(R.xml.tracker_app); // tracker app is your config xml: tracker_app.xml

            ga.enableAutoActivityReports(this);
        }

    }

    public Tracker getTracker(){
        startTracking();
        return mTracker;
    }
}
