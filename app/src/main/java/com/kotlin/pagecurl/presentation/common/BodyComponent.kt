package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import com.koduok.compose.navigation.Router
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.presentation.bookshelf.BookShelfComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.mypage.MyPageComponent
import com.kotlin.pagecurl.presentation.ranking.RankingComponent
import com.kotlin.pagecurl.presentation.store.StoreComponent

@Composable
fun BodyContentComponent(
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    Router<AppRoute>(start = HomeRoute) {
        when (it.data) {
            HomeRoute -> HomeViewComponent(homeViewModel, this@Router)
            Tab1Route -> RankingComponent(this@Router)
            Tab2Route -> BookShelfComponent(this@Router)
            Tab3Route -> StoreComponent(this@Router)
            Tab4Route -> MyPageComponent(this@Router)
            Tab5Route -> CurlViewComponent(curlViewModel, this@Router)
        }
    }
}
