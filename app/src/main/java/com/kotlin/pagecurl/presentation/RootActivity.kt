package com.kotlin.pagecurl.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.presentation.common.BodyContentComponent
import com.kotlin.pagecurl.viewModel.BookShelfViewModel
import com.kotlin.pagecurl.viewModel.CurlViewModel
import com.kotlin.pagecurl.viewModel.HomeViewModel

class RootActivity : AppCompatActivity() {

    private var isToastShowing: Boolean = false
    private val myApplication: MyApplication = MyApplication()
    private var homeViewModel: HomeViewModel = HomeViewModel(myApplication)
    private var curlViewModel: CurlViewModel = CurlViewModel(myApplication)
    private var bookShelfViewModel: BookShelfViewModel = BookShelfViewModel(myApplication)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        setContent {
            BodyContentComponent(
                homeViewModel
            )
        }
    }

    private fun setUpViewModel() {
        val viewModelProvider = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
        homeViewModel = viewModelProvider.get(HomeViewModel::class.java)
        curlViewModel = viewModelProvider.get(CurlViewModel::class.java)
        bookShelfViewModel = viewModelProvider.get(BookShelfViewModel::class.java)
    }

    override fun onBackPressed() {
    }

    private fun toastMake() {
        Toast.makeText(applicationContext, "もう一度戻るボタンを押すとアプリを終了します。", Toast.LENGTH_SHORT).show()
    }
}
