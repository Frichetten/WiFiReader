package io.github.frichetten.wifireader;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button findNetworks = (Button) findViewById(R.id.findNetworkButton);
        findNetworks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), findNetworks.class);
                startActivity(i);
            }
        });

        final Button getIP = (Button) findViewById(R.id.getIPButton);
        getIP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
                String name = wm.getConnectionInfo().toString();
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                Toast.makeText(getApplicationContext(), name.substring(0,name.indexOf(",")) + " IP: " + ip, Toast.LENGTH_LONG).show();
            }
        });

        final Button portScan = (Button) findViewById(R.id.portScanButton);
        portScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), portScan.class);
                startActivity(i);
            }
        });
    }
}

