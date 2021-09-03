package com.example.earlylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        var btnViewReport = findViewById<View>(R.id.btn_reports)
        var btnEcdResources = findViewById<View>(R.id.btn_ecd_resources)

        btnViewReport.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnEcdResources.setOnClickListener {
            val intent = Intent(this,Ecd_Resources::class.java)
            startActivity(intent)
        }
    }
}