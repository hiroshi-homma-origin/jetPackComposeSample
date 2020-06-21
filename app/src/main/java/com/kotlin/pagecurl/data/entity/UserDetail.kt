package com.kotlin.pagecurl.data.entity

import com.squareup.moshi.Json

data class UserDetail(
    val id: Int,
    val email: String?,
    @Json(name = "first_name") val firstName: String?,
    @Json(name = "last_name")val lastName: String?,
    val avatar: String?
)
