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
import com.google.android.material.card.MaterialCardView


class EcdResources : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)

        // Fonts
        val heading1 = findViewById<TextView>(R.id.headingText)
        val heading2 = findViewById<TextView>(R.id.headingTextNCF)
        val heading3 = findViewById<TextView>(R.id.headingTextLG)
        val heading4 = findViewById<TextView>(R.id.headingTextUnicef)
        val font: Typeface = Typeface.createFromAsset(assets, "Nexa Bold.otf")
        heading1.typeface = font
        heading2.typeface = font
        heading3.typeface = font
        heading4.typeface = font

        // Transparency of images
       // val learnImg = findViewById<ImageView>(R.id.limg);
       // learnImg.setAlpha(127)

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
}
