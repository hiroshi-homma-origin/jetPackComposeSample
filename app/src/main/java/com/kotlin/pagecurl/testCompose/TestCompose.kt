package com.kotlin.pagecurl.testCompose

import androidx.compose.Composable
import com.kotlin.pagecurl.data.AppContainer
import com.kotlin.pagecurl.data.interests.InterestsRepository
import com.kotlin.pagecurl.data.posts.PostsRepository
import com.kotlin.pagecurl.presentation.ranking.RankingComponent

@Composable
fun RootScreenViewTest(
    appContainer: AppContainer
) {
    AppContent(
        interestsRepository = appContainer.interestsRepository,
        postsRepository = appContainer.postsRepository
    )
}
@Composable
private fun AppContent(
    postsRepository: PostsRepository,
    interestsRepository: InterestsRepository
) {
//    RankingComponent(this@Router)
}
