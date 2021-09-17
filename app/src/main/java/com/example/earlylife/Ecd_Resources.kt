package com.example.earlylife
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Ecd_Resources : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecd__resources)
    }

    // Define list of ECD resources
    val arrayAdapter: ArrayAdapter<*>
    val resources = arrayOf("Resource 1", "Resource 2", "Resource 3", "Resource 4")
}