package com.kotlin.pagecurl.presentation.root

import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.currentScreen
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.viewExt.extcompose.BodyContentComponent

@Composable
fun RootScreenView(
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    Surface(color = MaterialTheme.colors.background) {
        BodyContentComponent(
            currentScreen = currentScreen,
            curlViewModel = curlViewModel,
            homeViewModel = homeViewModel
        )
    }
}
