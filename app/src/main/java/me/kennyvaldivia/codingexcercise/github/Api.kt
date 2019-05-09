package me.kennyvaldivia.codingexcercise.github

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    //urls
    @GET("search/repositories")
    fun projects(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int
    ): Call<ProjectsResults>
}