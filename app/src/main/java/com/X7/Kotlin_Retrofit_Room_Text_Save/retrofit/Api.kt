package com.X7.Kotlin_Retrofit_Room_Text_Save.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun getallPosts(): Call<ArrayList<JsonModel>>
}