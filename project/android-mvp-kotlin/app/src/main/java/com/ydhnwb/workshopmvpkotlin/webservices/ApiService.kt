package com.ydhnwb.workshopmvpkotlin.webservices

import com.ydhnwb.workshopmvpkotlin.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    fun allUsers() : Call<List<User>>

    @GET("users/{id}")
    fun findUser(@Path("id") user_id : Int) : Call<User>
}