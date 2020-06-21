package com.kotlin.pagecurl.data.interests.impl

import com.kotlin.pagecurl.data.interests.InterestsRepository
import com.kotlin.pagecurl.domainobject.model.Result

class FakeInterestsRepository : InterestsRepository {

    private val topics by lazy {
        mapOf(
            "Android" to listOf("Jetpack Compose", "Kotlin", "Jetpack"),
            "Programming" to listOf("Kotlin", "Declarative UIs", "Java"),
            "Technology" to listOf("Pixel", "Google")
        )
    }

    private val people by lazy {
        listOf(
            "Kobalt Toral",
            "K'Kola Uvarek",
            "Kris Vriloc",
            "Grala Valdyr",
            "Kruel Valaxar",
            "L'Elij Venonn",
            "Kraag Solazarn",
            "Tava Targesh",
            "Kemarrin Muuda"
        )
    }

    private val publications by lazy {
        listOf(
            "Kotlin Vibe",
            "Compose Mix",
            "Compose Breakdown",
            "Android Pursue",
            "Kotlin Watchman",
            "Jetpack Ark",
            "Composeshack",
            "Jetpack Point",
            "Compose Tribune"
        )
    }

    override fun getTopics(callback: (Result<Map<String, List<String>>>) -> Unit) {
        callback(Result.Success(topics))
    }

    override fun getPeople(callback: (Result<List<String>>) -> Unit) {
        callback(Result.Success(people))
    }

    override fun getPublications(callback: (Result<List<String>>) -> Unit) {
        callback(Result.Success(publications))
    }
}
