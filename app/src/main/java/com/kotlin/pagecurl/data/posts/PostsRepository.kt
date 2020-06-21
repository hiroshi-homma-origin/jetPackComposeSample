package com.kotlin.pagecurl.data.posts

import com.kotlin.pagecurl.domainobject.model.Post
import com.kotlin.pagecurl.domainobject.model.Result

interface PostsRepository {
    fun getPosts(callback: (Result<List<Post>>) -> Unit)
    fun getPost(postId: String, callback: (Result<Post?>) -> Unit)
}
