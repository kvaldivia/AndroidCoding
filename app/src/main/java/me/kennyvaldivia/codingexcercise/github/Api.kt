package me.kennyvaldivia.codingexcercise.github

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    //urls
    @get:GET("languages?q=kotlin")
    val users: Call<List<Project>>
}