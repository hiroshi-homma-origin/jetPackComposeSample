package com.kotlin.pagecurl.data.interests

import com.kotlin.pagecurl.domainobject.model.Result

interface InterestsRepository {

    fun getTopics(callback: (Result<Map<String, List<String>>>) -> Unit)

    fun getPeople(callback: (Result<List<String>>) -> Unit)

    fun getPublications(callback: (Result<List<String>>) -> Unit)
}
