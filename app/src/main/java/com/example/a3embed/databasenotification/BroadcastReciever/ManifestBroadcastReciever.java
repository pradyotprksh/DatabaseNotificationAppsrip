package com.example.a3embed.databasenotification.BroadcastReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.a3embed.databasenotification.R;

import java.util.Objects;

public class ManifestBroadcastReciever extends BroadcastReceiver {

    private static final String CHANNEL_ID = "CUSTOM_CHANNEL_ID";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);

        switch(Objects.requireNonNull(intent.getAction())){
            case "android.intent.action.AIRPLANE_MODE":
                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if(isAirplaneModeOn){
                    mBuilder.setContentTitle("AirPlane Mode");
                    mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Airplane Mode is ON"));
                    mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.notify(2, mBuilder.build());
                } else {
                    mBuilder.setContentTitle("AirPlane Mode");
                    mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Airplane Mode is OFF"));
                    mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.notify(2, mBuilder.build());
                }
                break;
            case "android.net.wifi.STATE_CHANGE":
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if(info != null && info.isConnected()) {
                    Toast.makeText(context, "WIFI on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "WIFI off", Toast.LENGTH_SHORT).show();
                }
                break;
            case "android.intent.action.BATTERY_CHANGED":
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                float batteryPct = level / (float)scale;
                Toast.makeText(context, String.valueOf(batteryPct), Toast.LENGTH_SHORT).show();
                break;
        }

    }
}