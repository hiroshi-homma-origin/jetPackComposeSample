package com.kotlin.pagecurl.data.posts.impl

import android.content.Context
import androidx.ui.graphics.imageFromResource
import com.kotlin.pagecurl.data.posts.PostsRepository
import com.kotlin.pagecurl.domainobject.model.Post
import com.kotlin.pagecurl.domainobject.model.Result

class BlockingFakePostsRepository(private val context: Context) : PostsRepository {
    private val postsWithResources: List<Post> by lazy {
        posts.map {
            it.copy(
                image = imageFromResource(context.resources, it.imageId),
                imageThumb = imageFromResource(context.resources, it.imageThumbId)
            )
        }
    }

    override fun getPost(postId: String, callback: (Result<Post?>) -> Unit) {
        callback(Result.Success(postsWithResources.find { it.id == postId }))
    }

    override fun getPosts(callback: (Result<List<Post>>) -> Unit) {
        callback(Result.Success(postsWithResources))
    }
}
