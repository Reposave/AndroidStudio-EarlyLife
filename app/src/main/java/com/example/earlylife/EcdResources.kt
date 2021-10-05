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

@Suppress("DEPRECATION")

class EcdResources : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)
        var tool_bar = findViewById<View>(R.id.toolbar)

        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        // Learn through Play card clicked
        val cardView = findViewById<CardView>(R.id.card_view)
        cardView.setOnClickListener { learnThroughPlay() }

        // Unicef card clicked
        val cardView1 = findViewById<CardView>(R.id.card_view1)
        cardView1.setOnClickListener { unicef() }

        // Learning Guidelines card clicked
        val cardView3 = findViewById<CardView>(R.id.card_view3)
        cardView3.setOnClickListener { learningGuidelines() }

        // NCF card clicked
        val cardView5 = findViewById<CardView>(R.id.card_view5)
        cardView5.setOnClickListener { NCF() }

        val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager

        //Perform a check if Wifi is connected to SmartQuilt
        Handler().postDelayed({
            if (wifiManager.isWifiEnabled) { // Wi-Fi adapter is ON
                val wifiInfo = wifiManager.connectionInfo
                if (wifiInfo.networkId == -1) {
                    //Toast.makeText(this, "SmartQuilt network not found."+count, Toast.LENGTH_LONG).show(); toasts are stacked.
                    // Not connected to an access point
                } else {
                    tool_bar.setBackgroundColor(Color.parseColor("#18a558"))
                    //Toast.makeText(this, "Connected to a network.", Toast.LENGTH_LONG).show();
                }
                // Connected to an access point
            } else {
                // Wi-Fi adapter is OFF
            }
        }, 4000)

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
        val unicefCard = findViewById<CardView>(R.id.card_view)
        unicefCard.visibility = View.GONE
        val learnThroughPlayCard = findViewById<CardView>(R.id.card_view1)
        learnThroughPlayCard.visibility = View.GONE
        val learningGuidelinesCard = findViewById<CardView>(R.id.card_view3)
        learningGuidelinesCard.visibility = View.GONE
        val ncfCard = findViewById<CardView>(R.id.card_view5)
        ncfCard.visibility = View.GONE
    }
}
