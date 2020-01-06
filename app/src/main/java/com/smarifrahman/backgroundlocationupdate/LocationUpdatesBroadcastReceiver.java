package com.smarifrahman.backgroundlocationupdate;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.location.LocationResult;

import java.util.List;


public class LocationUpdatesBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "LocationUpdatesBroadcas";
    public static final String ACTION_PROCESS_UPDATES = "com.smarifrahman.backgroundlocationupdate.action.PROCESS_UPDATES";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATES.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    List<Location> locations = result.getLocations();
                    LocationResultHelper locationResultHelper = new LocationResultHelper(
                            context, locations);
                    // Save the location data to SharedPreferences.
                    locationResultHelper.saveResults();
                    // Show notification with the location data.
                    locationResultHelper.showNotification();
                    Log.i(TAG, LocationResultHelper.getSavedLocationResult(context));
                }
            }
        }
    }
}
