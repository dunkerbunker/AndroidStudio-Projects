package com.example.fetchdatafromweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.fetchdatafromweb.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var getData: Button
    var TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData = findViewById(R.id.getData)

        getData.setOnClickListener {
            // methods variable is equals to getRetrofitInstance() method in RetrofitClient class
            val methods = RetrofitClient().getRetrofitInstance().create(Methods::class.java)
            var call: Call<Model> = methods.getAllData()

            call.enqueue(object : retrofit2.Callback<Model> {
                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                    // here we get the response from the server
                    // we can get the data from the response
                    // we can also get the status code from the response
                    Log.e(TAG, "onResponse: ${response.code()}")

                    var data: ArrayList<Model.Data> = response.body()!!.getData()

                    for (data1: Model.Data in data) {
                        Log.e(TAG, "onResponse: emails: ${data1.getEmail()}")
                    }

                }

                override fun onFailure(call: Call<Model>, t: Throwable) {
                    // here we get the error message
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }
    }
}