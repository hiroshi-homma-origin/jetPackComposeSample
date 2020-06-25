package com.kotlin.pagecurl.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.setContent
import com.koduok.compose.navigation.core.backStackController
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.root.RootScreenView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RootActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val curlViewModel: CurlViewModel by lazy { obtainViewModel() }
    private val homeViewModel: HomeViewModel by lazy { obtainViewModel1() }
    private fun obtainViewModel(): CurlViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(CurlViewModel::class.java)

    private fun obtainViewModel1(): HomeViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.fetchData()
        val appContainer = (application as MyApplication).container
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
