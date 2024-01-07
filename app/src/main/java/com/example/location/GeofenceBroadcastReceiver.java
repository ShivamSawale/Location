package com.example.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;

//public class GeofenceBroadcastReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent); // There's a breakpoint here
//        if (geofencingEvent.hasError()) {
//            String errorMessage = GeofenceStatusCodes
//                    .getStatusCodeString(geofencingEvent.getErrorCode());
//            Toast.makeText(context.getApplicationContext(), "error in broadcast receiver", Toast.LENGTH_SHORT).show();
 //           return;
//        }
  //       Get the transition type.
//        int geofenceTransition = geofencingEvent.getGeofenceTransition();
//
//        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
//                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
//
//            Toast.makeText(context.getApplicationContext(), "EVENT FIRED:" + (geofenceTransition == 1 ? "ENTERED" : "EXITED"), Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context.getApplicationContext(), "NOT ENTER NOR EXIT", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
//

//    private static final String TAG = "BroadcastReceiver";

//    @Override
//    public void onReceive(Context context, Intent intent) {
  //       TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
//
//
//        Toast.makeText(context, "Geofence Triggered...", Toast.LENGTH_SHORT).show();
//
//        Toast.makeText(context,"Geofence Triggered...", Toast.LENGTH_SHORT).show();
//                NotificationHelper notificationHelper = new NotificationHelper(context);

//        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

//        if (geofencingEvent.hasError()) {
//            Log.d(TAG, "onReceive: ERROR receiving geofence event...");
//            return;
//        }

//     List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
//        for (Geofence geofence: geofenceList) {
//            Log.d(TAG, "onReceive: " + geofence.getRequestId());
//        }
//        Location location = geofencingEvent.getTriggeringLocation();
//
//        int transitionType = geofencingEvent.getGeofenceTransition();
//
//        switch (transitionType){
//            case Geofence.GEOFENCE_TRANSITION_ENTER:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER","GEOFENCE_TRANSITION_ENTER",MainActivity.class);
//                break;
//
//            case Geofence.GEOFENCE_TRANSITION_DWELL:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL","GEOFENCE_TRANSITION_DWELL",MainActivity.class);
//                break;
//            case Geofence.GEOFENCE_TRANSITION_EXIT:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT","GEOFENCE_TRANSITION_EXIT",MainActivity.class);
//
//                break;
//        }
//    }
//}



    public class GeofenceBroadcastReceiver extends BroadcastReceiver {
        private static final String TAG = "GeofenceBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Geofence triggered!");



            // Extract geofence transition details from the broadcast intent
            GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
            if (geofencingEvent.hasError()) {
                Log.e(TAG, "GeofencingEvent error: " + geofencingEvent.getErrorCode());
                return;
            }

            // Get the geofence transition type
            int geofenceTransition = geofencingEvent.getGeofenceTransition();

            // Check if the transition type is ENTER or EXIT
            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                    geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                // Perform your desired actions based on the geofence transition
                if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                    Log.d(TAG, "Entered geofence");
                } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                    Log.d(TAG, "Exited geofence");
                }
            }
        }
    }
