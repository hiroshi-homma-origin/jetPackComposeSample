package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.material.DrawerState
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.viewModel.HomeViewModel

@Composable
fun BodyContentComponent(
    homeViewModel: HomeViewModel
) {
    val scaffoldState = remember { ScaffoldState() }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topBar = {
            TopAppBarScreen(scaffoldState, CurlViewStatus.selectIndex)
        },
        bottomBar = {
            BottomNavigationOnlySelectedLabelComponent()
        },
        bodyContent = {
            HomeViewComponent(homeViewModel)
        }
    )
}
