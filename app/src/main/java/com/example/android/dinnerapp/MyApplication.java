package com.example.android.dinnerapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.TagManager;

/**
 * Created by irahavoi on 2016-01-10.
 */
public class MyApplication extends Application{
    private Tracker mTracker;

    private ContainerHolder mContainerHolder;
    private TagManager mTagManager;


    public void startTracking(){
        if(mTracker == null){
            GoogleAnalytics ga = GoogleAnalytics.getInstance(this);

            mTracker = ga.newTracker(R.xml.tracker_app); // tracker app is your config xml: tracker_app.xml

            ga.enableAutoActivityReports(this);

            ga.getLogger()
                    .setLogLevel(Logger.LogLevel.VERBOSE);
        }

    }

    public Tracker getTracker(){
        startTracking();
        return mTracker;
    }

    public TagManager getTagManager(){
        if(mTagManager == null){
            mTagManager = TagManager.getInstance(this);
        }

        return mTagManager;
    }

    public ContainerHolder getContainerHolder() {
        return mContainerHolder;
    }

    public void setContainerHolder(ContainerHolder mContainerHolder) {
        this.mContainerHolder = mContainerHolder;
    }
}
