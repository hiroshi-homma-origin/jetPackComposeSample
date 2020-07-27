package com.kotlin.pagecurl.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.layout.Row
import androidx.ui.livedata.observeAsState
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.presentation.common.LiveDataLoadingComponent
import com.kotlin.pagecurl.viewModel.BookShelfViewModel
import com.kotlin.pagecurl.viewModel.SplashViewModel
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private val myApplication: MyApplication = MyApplication()
    private var splashViewModel: SplashViewModel = SplashViewModel(myApplication)
    private var bookShelfViewModel: BookShelfViewModel = BookShelfViewModel(myApplication)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("check_splash_pList:Start")
        setUpViewModel()
        splashViewModel.fetchData()
        bookShelfViewModel.fetchData()
        val intent = Intent(this, RootActivity::class.java)
        setContent {
            Row {
                LiveDataLoadingComponent()
            }
            val pList by splashViewModel.getData().observeAsState(initial = emptyList())
            Timber.d("check_splash_pList:$pList")
            if (pList.isNotEmpty()) {
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setUpViewModel() {
        val viewModelProvider = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
        splashViewModel = viewModelProvider.get(SplashViewModel::class.java)
    }
}
