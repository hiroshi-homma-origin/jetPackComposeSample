package com.kotlin.pagecurl.presentation.common

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.viewModel.HomeViewModel

@Composable
fun BodyContentComponent(
    homeViewModel: HomeViewModel
) {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        drawerContent = {
            AppDrawer(
                closeDrawer = { }
            )
        },
        topBar = {
            TopAppBarScreen(rememberScaffoldState(), 0)
        },
        bottomBar = {
            BottomNavigationOnlySelectedLabelComponent()
        },
        bodyContent = {
            HomeViewComponent(homeViewModel)
        }
    )
}
