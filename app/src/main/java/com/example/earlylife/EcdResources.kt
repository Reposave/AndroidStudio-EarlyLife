package com.example.earlylife
//import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.pdfview.PDFView
import android.graphics.Typeface
import android.widget.ImageView

import android.widget.TextView








class EcdResources : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)

        // Fonts
        val heading = findViewById<TextView>(R.id.headingText)
        val font: Typeface = Typeface.createFromAsset(assets, "Nexa_Bold.otf")
        /**val textView = findViewById<View>(R.id.textView) as TextView
        val font = Typeface.createFromAsset(assets, "fonts/FontName.ttf")*/
        heading.setTypeface(font)

        // Transparency of images
       // val learnImg = findViewById<ImageView>(R.id.limg);
       // learnImg.setAlpha(127)

        // Learn through Play card clicked
        val cardView = findViewById<CardView>(R.id.learnCard)
        cardView.setOnClickListener { learnThroughPlay() }
/**
        // Unicef card clicked
        val cardView1 = findViewById<CardView>(R.id.card_view1)
        cardView1.setOnClickListener { unicef() }

        // Learning Guidelines card clicked
        val cardView3 = findViewById<CardView>(R.id.card_view3)
        cardView3.setOnClickListener { learningGuidelines() }

        // NCF card clicked
        val cardView5 = findViewById<CardView>(R.id.card_view5)
        cardView5.setOnClickListener { NCF() }*/

    }



    private fun learnThroughPlay() {
        // Display Learn Through Play resource when selected
        val mPDFView = findViewById<PDFView>(R.id.earlyPdfView)
        mPDFView.fromAsset("earlychildhood_everyday_i_learn_through_play.pdf").show()
       // hide()
    }
/**
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
    }*/
}
