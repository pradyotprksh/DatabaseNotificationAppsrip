package com.example.a3embed.databasenotification.BroadcastReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.Objects;

public class ManifestBroadcastReciever extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        switch(Objects.requireNonNull(intent.getAction())){
            case "android.intent.action.AIRPLANE_MODE":
                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if(isAirplaneModeOn){
                    Toast.makeText(context, "AirPlane Mode on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "AirPlane Mode off", Toast.LENGTH_SHORT).show();
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