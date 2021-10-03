package com.example.earlylife
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.os.Environment
import java.io.File


class EcdResources : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)
        val cardView = findViewById<CardView>(R.id.card_view1)
        cardView.setOnClickListener{ learnToPlay() }


    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.card_view1 -> learnToPlay()
        }
    }

    private fun learnToPlay() {
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "app/src/main/assets/earlychildhood_everyday_i_learn_through_play.pdf")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(file), "application/pdf")
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
       /** val intent = Intent(this, PdfViewActivity::class.java)
        startActivity(intent)*/
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
