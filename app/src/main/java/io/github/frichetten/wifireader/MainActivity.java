package io.github.frichetten.wifireader;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the wifi managing objects
        WifiManager wim = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiScanReciever wifiReciever = new WifiScanReciever();
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        //Checking permissions. If there is none, get them. If there is, perform action
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
        }
        else{
            List<ScanResult> wifiScanList = wim.getScanResults();
            String data = wifiScanList.get(0).toString();
            int size = wifiScanList.size();
            Log.d("Result", String.valueOf(size));
        }
    }

    class WifiScanReciever extends BroadcastReceiver{
        public void onReceive(Context c, Intent intent){
        }
    }
}
