package com.example.exam_gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val image = findViewById<ImageView>(R.id.image)
        val btn_back = findViewById<Button>(R.id.btn_back)

        image.setImageResource(intent.getIntExtra("image", R.drawable.ic_launcher_background))

        btn_back.setOnClickListener {
            finish()
        }
    }
}