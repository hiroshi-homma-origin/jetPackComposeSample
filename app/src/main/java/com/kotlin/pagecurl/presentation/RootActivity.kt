package com.kotlin.pagecurl.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import com.koduok.compose.navigation.core.backStackController
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.setAllResetOffset
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

//    private val homeViewModel by viewModels<HomeViewModel>()
//    private val curlViewModel by viewModels<CurlViewModel>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        homeViewModel.fetchData()
        bookShelfViewModel.fetchData()
        setContent {
            BodyContentComponent(
                curlViewModel,
                homeViewModel,
                bookShelfViewModel
            )
        }
    }

    private fun setUpViewModel() {
        val viewModelProvider = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
        homeViewModel = viewModelProvider.get<HomeViewModel>(HomeViewModel::class.java)
        curlViewModel = viewModelProvider.get<CurlViewModel>(CurlViewModel::class.java)
        bookShelfViewModel = viewModelProvider.get<BookShelfViewModel>(BookShelfViewModel::class.java)
    }

    override fun onBackPressed() {
        if (!backStackController.pop()) {
            if (isToastShowing) {
                setAllResetOffset()
                super.onBackPressed()
                moveTaskToBack(true)
            } else {
                isToastShowing = true
                toastMake()
            }
        } else {
            CurlViewStatus.stack.map {
                backStackController.pop()
            }
            CurlViewStatus.selectIndex = 0
        }
    }

    private fun toastMake() {
        Toast.makeText(applicationContext, "もう一度戻るボタンを押すとアプリを終了します。", Toast.LENGTH_SHORT).show()
    }
}
