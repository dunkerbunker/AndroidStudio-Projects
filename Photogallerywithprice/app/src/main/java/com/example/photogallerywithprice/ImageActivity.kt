package com.example.photogallerywithprice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val image = findViewById<ImageView>(R.id.image)
        val price = findViewById<TextView>(R.id.image_price)
        val btn_back = findViewById<Button>(R.id.btn_back)

        image.setImageResource(intent.getIntExtra("image", R.drawable.ic_launcher_background))

        price.text = "Price: " + intent.getStringExtra("price")

        btn_back.setOnClickListener {
            finish()
        }
    }
}