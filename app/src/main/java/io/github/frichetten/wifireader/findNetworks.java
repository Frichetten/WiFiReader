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

        //Clean the scanResults into results.
        ArrayList<String> results = cleanResults(scanResults);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                results
        );
        lv.setAdapter(arrayAdapter);
    }

    private ArrayList<String> cleanResults(List<ScanResult> scanResults){
        ArrayList<String> results = new ArrayList<>();
        for(int i=0;i<scanResults.size();i++){
            String temp = scanResults.get(i).toString();
            String hold = "";
            //Adding the SSID
            if (temp.substring(0,temp.indexOf(",")).length() <= 6)
                hold = hold + "SSID: *HIDDEN*";
            else
                hold = hold + temp.substring(0,temp.indexOf(","));
            //Adding BSSID
            temp = temp.substring(temp.indexOf(",")+1);
            hold = hold + ", " + temp.substring(0,temp.indexOf(","));
            //Adding capabilities
            temp = temp.substring(temp.indexOf(",")+1);
            temp = temp.substring(temp.indexOf(",")+1);
            hold = hold + ", " + temp.substring(0,temp.indexOf(","));
            //Adding level
            temp = temp.substring(temp.indexOf(",")+1);
            hold = hold + ", " + temp.substring(0,temp.indexOf(","));

            results.add(hold);
        }
        return results;
    }

    private List<ScanResult> findNetworks(){
        //Creating the wifi managing objects
        WifiManager wim = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wim.startScan();
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
