package com.example.earlylife

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_tutorial.*

class Tutorial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        var position = 0
        val images = arrayListOf<Int>(R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5)

        val image = findViewById<ImageView>(R.id.tut_image)
        image.setImageResource(images[position])
        val btnNext = findViewById<Button>(R.id.btn_next)
        val btnPrev = findViewById<Button>(R.id.btn_previous)

        btnNext.setOnClickListener {
            if (position == 4){
                position = 0
            }
            else{
                position++
            }
            image.setImageResource(images[position])
        }

        btnPrev.setOnClickListener {
            if (position == 0){
                position = 4
            }
            else {
                position--
            }
            image.setImageResource(images[position])
        }



    }
}