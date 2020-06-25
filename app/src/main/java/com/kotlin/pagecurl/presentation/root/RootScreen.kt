package com.kotlin.pagecurl.presentation.root

import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.kotlin.pagecurl.presentation.common.BodyContentComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel

@Composable
fun RootScreenView(
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    Surface(color = MaterialTheme.colors.background) {
        BodyContentComponent(
            curlViewModel = curlViewModel,
            homeViewModel = homeViewModel
        )
    }
}
