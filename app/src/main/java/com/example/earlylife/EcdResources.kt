package com.example.earlylife
//import android.R
import android.graphics.Color
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.pdfview.PDFView
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.wifi.SupplicantState
import android.widget.ImageView

import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_connect_to_quilt.view.*

@Suppress("DEPRECATION")

class EcdResources : AppCompatActivity() {
    var ssid = "SmartQuilt"
    var key = "CID3208till"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)


        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)



        // Fonts
        setFonts()

        // Learn through Play card clicked
        val cardView = findViewById<CardView>(R.id.learnCard)
        cardView.setOnClickListener { learnThroughPlay() }

        // Unicef card clicked
        val cardView1 = findViewById<CardView>(R.id.UnicefCard)
        cardView1.setOnClickListener { unicef() }

        // Learning Guidelines card clicked
        val cardView3 = findViewById<CardView>(R.id.lgCard)
        cardView3.setOnClickListener { learningGuidelines() }

        // NCF card clicked
        val cardView5 = findViewById<CardView>(R.id.ncfCard)
        cardView5.setOnClickListener { NCF() }

        checkWifi()
    }
    fun checkWifi(){
        //Check Which network we're connected to.
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        if (info != null && info.isConnected) {
            val wifiName = info.extraInfo

            if(wifiName=="\""+ssid+"\"") {
                var tool_bar = findViewById<View>(R.id.toolbar)
                tool_bar.setBackgroundColor(Color.parseColor("#18a558"))
            }
            //Toast.makeText(this, wifiName, Toast.LENGTH_LONG).show();
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

    private fun learnThroughPlay() {
        // Display Learn Through Play resource when selected
        val mPDFView = findViewById<PDFView>(R.id.activityMainPdfView)
        mPDFView.fromAsset("earlychildhood_everyday_i_learn_through_play.pdf").show()
        hide()
    }

    private fun unicef(){
        // Display UNICEF pdf resource when selected
        val mPDFView = findViewById<PDFView>(R.id.activityMainPdfView)
        mPDFView.fromAsset("unicef.pdf").show()
        hide()
    }
    private fun learningGuidelines(){
        // Display Learning guidelines pdf resource when selected
        val mPDFView = findViewById<PDFView>(R.id.activityMainPdfView)
        mPDFView.fromAsset("lg.pdf").show()
        hide()
    }
    private fun NCF(){
        // Display NCF pdf resource when selected
        val mPDFView = findViewById<PDFView>(R.id.activityMainPdfView)
        mPDFView.fromAsset("ncf.pdf").show()
        hide()
    }

    private fun hide(){
        /** This function hides all the cards while a resource is being viewed*/
        val unicefCardGone = findViewById<CardView>(R.id.UnicefCard)
        unicefCardGone.visibility = View.GONE
        val learnThroughPlayCardGone = findViewById<MaterialCardView>(R.id.learnCard)
        learnThroughPlayCardGone.visibility = View.GONE
        val learningGuidelinesCardGone = findViewById<CardView>(R.id.lgCard)
        learningGuidelinesCardGone.visibility = View.GONE
        val ncfCardGone = findViewById<CardView>(R.id.ncfCard)
        ncfCardGone.visibility = View.GONE
    }

    private fun setFonts(){
        /** This function sets all the fonts for the ECDResources page */
        val fontBold: Typeface = Typeface.createFromAsset(assets, "Nexa Bold.otf")
        val fontLight: Typeface = Typeface.createFromAsset(assets, "Nexa Light.otf")

        val heading1 = findViewById<TextView>(R.id.headingText)
        val heading2 = findViewById<TextView>(R.id.headingTextNCF)
        val heading3 = findViewById<TextView>(R.id.headingTextLG)
        val heading4 = findViewById<TextView>(R.id.headingTextUnicef)
        val sec1 = findViewById<TextView>(R.id.secText1)
        val sec2 = findViewById<TextView>(R.id.secText2)
        val sec3 = findViewById<TextView>(R.id.secText3)
        val sec4 = findViewById<TextView>(R.id.secText4)

        // apply font
        heading1.typeface = fontBold
        heading2.typeface = fontBold
        heading3.typeface = fontBold
        heading4.typeface = fontBold
        sec1.typeface = fontLight
        sec2.typeface = fontLight
        sec3.typeface = fontLight
        sec4.typeface = fontLight

    }
}
