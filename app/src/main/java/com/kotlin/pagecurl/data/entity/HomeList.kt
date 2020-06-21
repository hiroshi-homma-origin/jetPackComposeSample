package com.kotlin.pagecurl.data.entity

import com.squareup.moshi.Json

data class HomeList(
    val page: Int,
    @Json(name = "per_page") val perPage: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val data: List<UserDetail>,
    val ad: AdInfo
)
