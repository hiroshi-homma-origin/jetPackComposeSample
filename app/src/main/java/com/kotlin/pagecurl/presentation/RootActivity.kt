package com.kotlin.pagecurl.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import com.koduok.compose.navigation.core.backStackController
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.root.RootScreenView
import dagger.android.support.DaggerAppCompatActivity

class RootActivity : DaggerAppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel = ViewModelProvider(
            viewModelStore, defaultViewModelProviderFactory
        ).get<HomeViewModel>(HomeViewModel::class.java)

        val curlViewModel: CurlViewModel = ViewModelProvider(
            viewModelStore, defaultViewModelProviderFactory
        ).get<CurlViewModel>(CurlViewModel::class.java)

        homeViewModel.fetchData()
        setContent {
            RootScreenView(
                curlViewModel,
                homeViewModel
            )
        }
    }

    override fun onBackPressed() {
        if (!backStackController.pop()) {
            super.onBackPressed()
        } else {
            CurlViewStatus.stack.removeAt(CurlViewStatus.stack.lastIndex)
            CurlViewStatus.selectIndex = CurlViewStatus.stack.get(CurlViewStatus.stack.lastIndex)
        }
    }
}
