package com.example.hreeels.pakuclient;

import android.app.Application;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.UUID;

/**
 * Created by Hreeels on 2016-03-19.
 */
public class MyApplication extends Application {

    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        14421, 31585));
            }
        });
    }

}
