package com.example.rafaelmatucheski.street2androidproject;
import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


/**
 * Created by Rafael Matucheski on 16/04/2017.
 */

public class Street2App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }
}

