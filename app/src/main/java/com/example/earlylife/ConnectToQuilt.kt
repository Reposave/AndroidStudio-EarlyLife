package com.example.earlylife

import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class ConnectToQuilt : AppCompatActivity() {
    var edittext: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_to_quilt)
        var btnWifiConnect = findViewById<View>(R.id.btn_WifiConnect)

        btnWifiConnect.setOnClickListener {
            startActivity( Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
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
}