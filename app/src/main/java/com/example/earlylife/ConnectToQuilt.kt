package com.example.earlylife

import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.net.wifi.WifiConfiguration
import java.lang.String
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_connect_to_quilt.view.*


@Suppress("DEPRECATION")

class ConnectToQuilt : AppCompatActivity() {
    var edittext: EditText? = null
    private val REQUEST_CHANGE_WIFI_STATE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_to_quilt)
        var instructText = findViewById<View>(R.id.textView2)
        var btnWifiConnect = findViewById<View>(R.id.btn_WifiConnect)
        var ssid = "SmartQuilt"
        var key = "CID3208till"


        btnWifiConnect.setOnClickListener {
            //startActivity( Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
            val permissionCheck =
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CHANGE_WIFI_STATE
                )

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CHANGE_WIFI_STATE), REQUEST_CHANGE_WIFI_STATE)
            } else {
                Toast.makeText(this, "SmartQuilt network not found.", Toast.LENGTH_LONG).show();
                instructText.textView2.text = getString(R.string.instructions3)
            }

            val wifiConfig = WifiConfiguration()
            wifiConfig.SSID = String.format("\"%s\"", ssid)
            wifiConfig.preSharedKey = String.format("\"%s\"", key)

            val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
//remember id
//remember id
            val netId = wifiManager.addNetwork(wifiConfig)
            wifiManager.disconnect()
            wifiManager.enableNetwork(netId, true)
            wifiManager.reconnect()
        }

    }
    /*private fun addKeyListener() {

        // get edittext component
         edittext = findViewById<EditText?>(R.id.WifiName);

        // add a keylistener to monitor the keaybord avitvity...
        edittext.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // if the users pressed a button and that button was "0"
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {

                    // display the input text....
                    Toast.makeText(MainActivity.this,edittext.getText(), Toast.LENGTH_LONG).show();
                    return true;

                    // if the users pressed a button and that button was "9"
                } else if ((event.getAction() == KeyEvent.ACTION_DOWN)  && (keyCode == KeyEvent.KEYCODE_9)) {

                    // display message
                    Toast.makeText(MainActivity.this, "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }*/


    override fun onRequestPermissionsResult(
        requestCode: Int,
        vararg permissions: kotlin.String?,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CHANGE_WIFI_STATE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Excellent! Connected to Smart Quilt.", Toast.LENGTH_LONG).show();
            }
            else -> {
                //This doesn't seem to be executed.
                Toast.makeText(this, "onRequestPermissionsResult() Fail Condition", Toast.LENGTH_LONG).show();
            }
        }
    }
}