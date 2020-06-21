package com.kotlin.pagecurl.data.posts.impl

import android.content.res.Resources
import android.os.Handler
import androidx.ui.graphics.imageFromResource
import com.kotlin.pagecurl.data.posts.PostsRepository
import com.kotlin.pagecurl.domainobject.model.Post
import com.kotlin.pagecurl.domainobject.model.Result
import java.util.concurrent.ExecutorService
import kotlin.random.Random.Default.nextFloat

class FakePostsRepository(
    private val executorService: ExecutorService,
    private val resultThreadHandler: Handler,
    private val resources: Resources
) : PostsRepository {

    private val postsWithResources: List<Post> by lazy {
        posts.map {
            it.copy(
                image = imageFromResource(resources, it.imageId),
                imageThumb = imageFromResource(resources, it.imageThumbId)
            )
        }
    }

    override fun getPost(postId: String, callback: (Result<Post?>) -> Unit) {
        executeInBackground(callback) {
            resultThreadHandler.post {
                callback(
                    Result.Success(
                        postsWithResources.find { it.id == postId }
                    )
                )
            }
        }
    }

    override fun getPosts(callback: (Result<List<Post>>) -> Unit) {
        executeInBackground(callback) {
            simulateNetworkRequest()
            Thread.sleep(1500L)
            if (shouldRandomlyFail()) {
                throw IllegalStateException()
            }
            resultThreadHandler.post { callback(Result.Success(postsWithResources)) }
        }
    }

    /**
     * Executes a block of code in the past and returns an error in the [callback]
     * if [block] throws an exception.
     */
    private fun executeInBackground(callback: (Result<Nothing>) -> Unit, block: () -> Unit) {
        executorService.execute {
            try {
                block()
            } catch (e: Exception) {
                resultThreadHandler.post { callback(Result.Error(e)) }
            }
        }
    }

    /**
     * Simulates network request
     */
    private var networkRequestDone = false
    private fun simulateNetworkRequest() {
        if (!networkRequestDone) {
            Thread.sleep(2000L)
            networkRequestDone = true
        }
    }

    /**
     * 1/3 requests should fail loading
     */
    private fun shouldRandomlyFail(): Boolean = nextFloat() < 0.33f
}
