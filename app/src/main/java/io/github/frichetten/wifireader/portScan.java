package io.github.frichetten.wifireader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class portScan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_scan);
        setTitle("Port Scan");
    }
}
