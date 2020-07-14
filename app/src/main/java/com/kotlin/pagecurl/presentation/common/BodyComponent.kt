package com.kotlin.pagecurl.presentation.common

import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.animation.Crossfade
import androidx.ui.material.DrawerState
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import com.koduok.compose.navigation.Router
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen6
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.selectIndex
import com.kotlin.pagecurl.presentation.bookshelf.BookShelfComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewComponent
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.presentation.mypage.MyPageComponent
import com.kotlin.pagecurl.presentation.ranking.RankingComponent
import com.kotlin.pagecurl.presentation.store.StoreComponent
import com.kotlin.pagecurl.viewModel.BookShelfViewModel
import com.kotlin.pagecurl.viewModel.CurlViewModel
import com.kotlin.pagecurl.viewModel.HomeViewModel

@Composable
fun BodyContentComponent(
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel,
    bookShelfViewModel: BookShelfViewModel
) {
    val scaffoldState = remember { ScaffoldState() }
    Router<AppRoute>(start = HomeRoute) { route ->
        if (selectIndex == Screen6.ordinal) {
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = {
                    BottomNavigationOnlySelectedLabelComponent(this@Router)
                },
                bodyContent = {
                    Crossfade(
                        current = route.data,
                        animation = TweenBuilder()
                    ) {
                        when (route.data) {
                            HomeRoute -> HomeViewComponent(homeViewModel)
                            Tab1Route -> RankingComponent()
                            Tab2Route -> BookShelfComponent(bookShelfViewModel)
                            Tab3Route -> StoreComponent()
                            Tab4Route -> MyPageComponent()
                            Tab5Route -> CurlViewComponent(curlViewModel)
                        }
                    }
                }
            )
        } else {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = {
                    AppDrawer(
                        closeDrawer = { scaffoldState.drawerState = DrawerState.Closed },
                        backStack = this@Router
                    )
                },
                topBar = {
                    TopAppBarScreen(scaffoldState, selectIndex)
                },
                bottomBar = {
                    BottomNavigationOnlySelectedLabelComponent(this@Router)
                },
                bodyContent = {
                    Crossfade(
                        current = route.data,
                        animation = TweenBuilder()
                    ) {
                        when (route.data) {
                            HomeRoute -> HomeViewComponent(homeViewModel)
                            Tab1Route -> RankingComponent()
                            Tab2Route -> BookShelfComponent(bookShelfViewModel)
                            Tab3Route -> StoreComponent()
                            Tab4Route -> MyPageComponent()
                            Tab5Route -> CurlViewComponent(curlViewModel)
                        }
                    }
                }
            )
        }
    }
}
