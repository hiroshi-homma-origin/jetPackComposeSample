package com.kotlin.pagecurl.data.api

import com.kotlin.pagecurl.data.entity.HomeList
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectService {
    @GET("api/users")
    suspend fun getUserList(@Query("per_page") number: Int): HomeList
}
