package com.example.earlylife

//import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.pdfview.PDFView


class EcdResources : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)
        val cardView = findViewById<CardView>(R.id.card_view5)
        cardView.setOnClickListener { learnToPlay() }


    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.card_view1 -> learnToPlay()
        }
    }

    private fun learnToPlay() {
        //val file = File(Environment.getExternalStorageDirectory().absolutePath + "app/src/main/assets/earlychildhood_everyday_i_learn_through_play.pdf")
        //val intent = Intent(Intent.ACTION_VIEW)
        // val imagePath: File = File(Context.getFilesDir(), "r1")
        val mPDFView = findViewById<PDFView>(R.id.activityMainPdfView)
        mPDFView.fromAsset("earlychildhood_everyday_i_learn_through_play.pdf").show()
        // Hiding cards
        val unicef = findViewById<CardView>(R.id.card_view)
        unicef.visibility = View.GONE

    }
}
/**File file = new File("/app/assets/earlychildhood_everyday_i_learn_through_play.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "pdf/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent); // Crashes on this line*/
    }




}
/*
private class CustomAdapter(context : Context): BaseAdapter(){

    private val aContext : Context

    init{
        aContext = context
    }
    // Responsible for rows in list
    override fun getCount(): Int {
        TODO("Not yet implemented")
        return 5
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return "Test String"
    }

    // Renders out each row
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val textView = TextView(aContext)
        textView.text = "HERE is my ROW for my LISTVIEW"
        return textView
    }
}*/