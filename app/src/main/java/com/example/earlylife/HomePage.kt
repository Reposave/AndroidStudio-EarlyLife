package com.example.earlylife

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull

@Suppress("DEPRECATION")

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var btnViewReport = findViewById<View>(R.id.view_report_card)
        var btnEcdResources = findViewById<View>(R.id.view_ecd_resources_btn)
        var btnConnectToQuilt = findViewById<View>(R.id.connect_to_quilt_card)

        title = ""

        setFonts()

        btnViewReport.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnEcdResources.setOnClickListener {
            val intent = Intent(this,EcdResources::class.java)
            startActivity(intent)
        }

        btnConnectToQuilt.setOnClickListener {
            val intent = Intent(this,ConnectToQuilt::class.java)
            startActivity(intent)
        }
    }
    private fun setFonts(){
        /** This function sets all the fonts for the ECDResources page */
        val fontBold: Typeface = Typeface.createFromAsset(assets, "Nexa Bold.otf")
        val fontLight: Typeface = Typeface.createFromAsset(assets, "Nexa Light.otf")

        val heading1 = findViewById<TextView>(R.id.textView)
        val heading2 = findViewById<TextView>(R.id.report)
        val heading3 = findViewById<TextView>(R.id.resources)
        val heading4 = findViewById<TextView>(R.id.connect)
        val heading5 = findViewById<TextView>(R.id.upload)

        // apply font
        heading1.typeface = fontBold
        heading2.typeface = fontBold
        heading3.typeface = fontBold
        heading4.typeface = fontBold
        heading5.typeface = fontBold


    }
}