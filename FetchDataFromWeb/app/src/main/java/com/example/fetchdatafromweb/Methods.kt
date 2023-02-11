package com.example.fetchdatafromweb

import retrofit2.Call
import retrofit2.http.GET

interface Methods {

    @GET("api/users?page=2")
    fun getAllData(): Call<Model>
}