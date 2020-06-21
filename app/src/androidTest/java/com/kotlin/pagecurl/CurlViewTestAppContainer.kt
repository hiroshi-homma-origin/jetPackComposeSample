package com.kotlin.pagecurl

import android.content.Context
import com.kotlin.pagecurl.data.AppContainer
import com.kotlin.pagecurl.data.interests.InterestsRepository
import com.kotlin.pagecurl.data.interests.impl.FakeInterestsRepository
import com.kotlin.pagecurl.data.posts.PostsRepository
import com.kotlin.pagecurl.data.posts.impl.BlockingFakePostsRepository

class CurlViewTestAppContainer(private val context: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        BlockingFakePostsRepository(context)
    }

    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}
