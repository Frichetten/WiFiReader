package io.github.frichetten.wifireader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class findNetworks extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_networks);
        setTitle("Find Network");

        lv = (ListView) findViewById(R.id.findNetworkListView);
        List<ScanResult> scanResults = findNetworks();
        ArrayList<String> results = new ArrayList<>();
        for(int i=0;i<scanResults.size();i++){
            results.add(scanResults.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                results
        );
        lv.setAdapter(arrayAdapter);
    }

    private List<ScanResult> findNetworks(){
        //Creating the wifi managing objects
        WifiManager wim = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiScanReceiver wifiReceiver = new WifiScanReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        //Checking permissions. If there is none, get them. If there is, perform action
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
        }
        else{
            return wim.getScanResults();
        }
        return null;
    }
    class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent){
        }
    }
}
