package com.example.earlylife

import android.content.ContentValues
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.net.wifi.WifiConfiguration
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_connect_to_quilt.view.*
import android.net.wifi.WifiInfo
import android.os.Handler
import android.provider.BaseColumns
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.NonNull
import com.example.earlylife.Models.Quilt
import com.example.earlylife.Retrofit.RetrofitService
import com.example.earlylife.SQLite.FeedReaderContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

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

            //Find a way to create a delay before running this next section of code.
            instructText.textView2.text = getString(R.string.instructions6)

            Handler().postDelayed({
                if (wifiManager.isWifiEnabled) { // Wi-Fi adapter is ON
                    val wifiInfo = wifiManager.connectionInfo
                    if (wifiInfo.networkId == -1) {
                        //Toast.makeText(this, "SmartQuilt network not found."+count, Toast.LENGTH_LONG).show(); toasts are stacked.
                        instructText.textView2.text = getString(R.string.instructions3)
                        // Not connected to an access point
                    } else {
                        Toast.makeText(this, "Connected to a network.", Toast.LENGTH_LONG).show();
                        //Add download code.
                        DownloadData()
                        instructText.textView2.text = getString(R.string.instructions5)

                        //Add delay.
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    // Connected to an access point
                } else {
                    instructText.textView2.text = getString(R.string.instructions4)
                    // Wi-Fi adapter is OFF
                }
            }, 4000)

            //checkWifiOnAndConnected()

        }

    }
    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
    private fun DownloadData(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.ServiceBuilder.buildService().getShapes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)
        Log.d("ERROR",t.toString())

    }

    private fun onResponse(response: Quilt) {
        Log.d("Response",response.toString())

        //saving to database
        val dbHelper = FeedReaderContract.FeedReaderDbHelper(this.applicationContext)
        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val learnShapesValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.LearnShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.LearnShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.LearnShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.LearnShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.LearnShapes.correct)
        }

        val learnNumbersValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.LearnShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.LearnShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.LearnShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.LearnShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.LearnShapes.correct)
        }
        /*
        val loveValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.Love.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.Love.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.Love.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.Love.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.Love.correct)
        }
        val matchShapesValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.MarchShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.MarchShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.MarchShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.MarchShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.MarchShapes.correct)
        }

 */

        Log.d("Debug","Values created")
        // Insert the new row, returning the primary key value of the new row
        var newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, learnShapesValues)
        //newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, loveValues)

        //reading the values from database
        val dbr = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK,
            FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)

        val cursor = dbr.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val itemIds = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE))
                itemIds.add(itemId)
            }
        }
        cursor.close()
        Log.e("Database", itemIds.toString())
    }
}