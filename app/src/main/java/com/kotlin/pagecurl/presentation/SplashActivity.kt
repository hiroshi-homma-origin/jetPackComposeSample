package com.kotlin.pagecurl.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.api.fragment.Pokemon
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
        setUpViewModel()
        splashViewModel.fetchData()
        bookShelfViewModel.fetchData()
        val intent = Intent(this, RootActivity::class.java)
        setContent {
            Row { LiveDataLoadingComponent() }
            if (observe().isNotEmpty()) {
                startActivity(intent)
                finish()
            }
        }
    }

    @Composable
    fun observe(): List<Pokemon> {
        val pList by splashViewModel.getData().observeAsState(initial = emptyList())
        return pList
    }

    private fun setUpViewModel() {
        val viewModelProvider = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
        splashViewModel = viewModelProvider.get(SplashViewModel::class.java)
    }
}
