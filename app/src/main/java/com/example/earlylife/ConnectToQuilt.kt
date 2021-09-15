package com.example.earlylife

import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ConnectToQuilt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_to_quilt)
        var btnWifiConnect = findViewById<View>(R.id.btn_WifiConnect)

        btnWifiConnect.setOnClickListener {
            startActivity( Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
        }
    }
}