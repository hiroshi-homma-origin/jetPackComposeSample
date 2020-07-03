package com.kotlin.pagecurl.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import com.koduok.compose.navigation.core.backStackController
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.common.BodyContentComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel

class RootActivity : AppCompatActivity() {

    private val myApplication: MyApplication = MyApplication()
    private var homeViewModel: HomeViewModel = HomeViewModel(myApplication)
    private var curlViewModel: CurlViewModel = CurlViewModel(myApplication)

//    private val homeViewModel by viewModels<HomeViewModel>()
//    private val curlViewModel by viewModels<CurlViewModel>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        homeViewModel.fetchData()
        setContent {
            BodyContentComponent(
                curlViewModel,
                homeViewModel
            )
        }
    }

    private fun setUpViewModel() {
        val viewModelProvider = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
        homeViewModel = viewModelProvider.get<HomeViewModel>(HomeViewModel::class.java)
        curlViewModel = viewModelProvider.get<CurlViewModel>(CurlViewModel::class.java)
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
