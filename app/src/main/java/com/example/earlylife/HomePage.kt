package com.example.earlylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        var btnViewReport = findViewById<View>(R.id.view_report_card)
        var btnEcdResources = findViewById<View>(R.id.view_ecd_resources_btn)
        var btnConnectToQuilt = findViewById<View>(R.id.connect_to_quilt_card)

        btnViewReport.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnEcdResources.setOnClickListener {
            val intent = Intent(this,Ecd_Resources::class.java)
            startActivity(intent)
        }

        btnConnectToQuilt.setOnClickListener {
            val intent = Intent(this,ConnectToQuilt::class.java)
            startActivity(intent)
        }
    }
}