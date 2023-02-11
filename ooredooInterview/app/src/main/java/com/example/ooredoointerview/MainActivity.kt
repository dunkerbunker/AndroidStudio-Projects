package com.example.ooredoointerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// base url to get all data
const val BASE_URL = "https://api.nasa.gov/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // policy for network on main thread
        // note that there is also permission required in manifest
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // function to get data
        getMyData()
    }

    private fun getMyData() {
        // using retrofit implementation to get data
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL) // base url to get all data, aka the domain
            .build()
            .create(ApiInterface::class.java) // calling the interface to get data

        val retrofitData = retrofitBuilder.getData() // calling the function to get data from the API interface file

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                // if the data is successfully retrieved
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                // if the data is successfully retrieved
                if (response.isSuccessful) {
                    // getting the data from the response
                    val responseBody = response.body()!!

                    // get views
                    var title = findViewById<TextView>(R.id.title)
                    var explanation = findViewById<TextView>(R.id.explanation)
                    var date = findViewById<TextView>(R.id.date)
                    var img = findViewById<ImageView>(R.id.image)

                    // update data to views
                    title.text = responseBody[0].title
                    explanation.text = responseBody[0].explanation
                    date.text = responseBody[0].date

                    // update image using glide plugin
                    var imageUrl = responseBody[0].hdurl
                    Glide.with(this).load(imageUrl).into(img)

                    Log.d("Response", responseBody.toString())
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", t.message.toString())
            }
        })
    }
}