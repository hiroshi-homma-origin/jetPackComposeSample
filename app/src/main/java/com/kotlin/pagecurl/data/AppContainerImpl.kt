package com.kotlin.pagecurl.data

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.kotlin.pagecurl.data.interests.InterestsRepository
import com.kotlin.pagecurl.data.interests.impl.FakeInterestsRepository
import com.kotlin.pagecurl.data.posts.PostsRepository
import com.kotlin.pagecurl.data.posts.impl.FakePostsRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

interface AppContainer {
    val postsRepository: PostsRepository
    val interestsRepository: InterestsRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    private val executorService: ExecutorService by lazy {
        Executors.newFixedThreadPool(4)
    }

    private val mainThreadHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository(
            executorService = executorService,
            resultThreadHandler = mainThreadHandler,
            resources = applicationContext.resources
        )
    }

    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}
