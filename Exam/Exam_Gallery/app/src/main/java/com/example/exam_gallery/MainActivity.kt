package com.example.exam_gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

// mainactivity also implements the onclicklistener
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image1 = findViewById<ImageView>(R.id.image1)
        val image2 = findViewById<ImageView>(R.id.image2)
        val image3 = findViewById<ImageView>(R.id.image3)
        val image4 = findViewById<ImageView>(R.id.image4)
        val image5 = findViewById<ImageView>(R.id.image5)
        val image6 = findViewById<ImageView>(R.id.image6)
        val image7 = findViewById<ImageView>(R.id.image7)
        val image8 = findViewById<ImageView>(R.id.image8)
        val image9 = findViewById<ImageView>(R.id.image9)

        // set the onclicklistener to the images
        image1.setOnClickListener(this)
        image2.setOnClickListener(this)
        image3.setOnClickListener(this)
        image4.setOnClickListener(this)
        image5.setOnClickListener(this)
        image6.setOnClickListener(this)
        image7.setOnClickListener(this)
        image8.setOnClickListener(this)
        image9.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        // if the image is clicked, intent to the second activity

        when (v?.id) {
            R.id.image1 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image1)
                startActivity(intent)
            }
            R.id.image2 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image2)
                startActivity(intent)
            }
            R.id.image3 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image3)
                startActivity(intent)
            }
            R.id.image4 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image4)
                startActivity(intent)
            }
            R.id.image5 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image5)
                startActivity(intent)
            }
            R.id.image6 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image6)
                startActivity(intent)
            }
            R.id.image7 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image7)
                startActivity(intent)
            }
            R.id.image8 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image8)
                startActivity(intent)
            }
            R.id.image9 -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("image", R.drawable.image9)
                startActivity(intent)
            }
        }
    }
}