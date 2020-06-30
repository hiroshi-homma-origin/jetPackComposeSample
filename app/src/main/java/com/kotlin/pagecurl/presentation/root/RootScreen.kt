package com.kotlin.pagecurl.presentation.root

import androidx.compose.Composable
import com.kotlin.pagecurl.presentation.common.BodyContentComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel

@Composable
fun RootScreenView(
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    BodyContentComponent(
        curlViewModel = curlViewModel,
        homeViewModel = homeViewModel
    )
}
