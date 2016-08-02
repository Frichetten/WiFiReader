package io.github.frichetten.wifireader;

import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class portScan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_scan);
        setTitle("Port Scan");

        //Attempt host discovery
        try {
            WifiManager wim = (WifiManager) getSystemService(WIFI_SERVICE);
            String ip2 = Formatter.formatIpAddress(wim.getConnectionInfo().getIpAddress());
            Log.d("Result",ip2);
            //byte[] ip = (Byte[])ip2;
            for (int i = 2; i < 254; i++) {
                String ip = "192.168.1." + String.valueOf(i);
                InetAddress address = InetAddress.getByName(ip);
                if(address.isReachable(1000))
                    Log.d("Result", String.valueOf(ip) + " is reachable");
            }
        }
        catch (java.net.UnknownHostException e){Log.d("Result","Unknown Host Exception");}
        catch (java.io.IOException e){Log.d("Result","IO Exception");}

    }
}
