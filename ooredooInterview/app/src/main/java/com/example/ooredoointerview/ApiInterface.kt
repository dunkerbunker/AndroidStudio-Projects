package com.example.ooredoointerview

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    // below line just goes to the url and fetches the data. it adds onto the base url
    @GET("planetary/apod?api_key=DEMO_KEY")
    fun getData(): Call<List<MyDataItem>>

}