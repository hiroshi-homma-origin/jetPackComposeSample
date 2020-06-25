package com.kotlin.pagecurl.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import com.koduok.compose.navigation.core.backStackController
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.root.RootScreenView

class RootActivity : AppCompatActivity() {

    private var homeViewModel: HomeViewModel = HomeViewModel()
    private var curlViewModel: CurlViewModel = CurlViewModel()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        homeViewModel.fetchData()
        setContent {
            RootScreenView(
                curlViewModel,
                homeViewModel
            )
        }
    }

    private fun setUpViewModel() {
        homeViewModel = ViewModelProvider(
            viewModelStore, defaultViewModelProviderFactory
        ).get<HomeViewModel>(HomeViewModel::class.java)

        curlViewModel = ViewModelProvider(
            viewModelStore, defaultViewModelProviderFactory
        ).get<CurlViewModel>(CurlViewModel::class.java)
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
