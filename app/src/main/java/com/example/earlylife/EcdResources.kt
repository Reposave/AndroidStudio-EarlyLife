package com.example.earlylife
import android.content.Context
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EcdResources : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)
        //Access ListView from XML file
        val listView = findViewById<ListView>(R.id.main_listview)


        // Adapter tells list what to render
        listView.adapter = CustomAdapter(this)

       /** // Define list of ECD resources
        val arrayAdapter: ArrayAdapter<String>
        val resources = arrayOf("Resource 1", "Resource 2", "Resource 3", "Resource 4")

        // Access ListView from XML file
        var listView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources)
        listView.adapter = arrayAdapter*/
    }




}

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
}
