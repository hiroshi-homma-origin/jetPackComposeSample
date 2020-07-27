package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.domainobject.usecase.MotionEventSetting
import com.kotlin.pagecurl.viewExt.curl.base.CurlView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class CurlViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val curlLiveData: MutableLiveData<CurlView>? = null
    var curlCurrentIndex = 0
    var isCommandButtonOpen = false

    fun curlViewLayoutSetBackMotionEvent(curl: CurlView, orientation: Int) {
        curlLiveData?.postValue(curl)
        viewModelScope.launch(Dispatchers.Default) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                MotionEventSetting.motionEventLandBackImp(curl)
            } else {
                MotionEventSetting.motionEventPortBackImp(curl)
            }
        }
        curlCurrentIndex = curl.currentIndex
    }

    fun curlViewLayoutSetNextMotionEvent(curl: CurlView, orientation: Int) {
        curlLiveData?.postValue(curl)
        viewModelScope.launch(Dispatchers.Default) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                MotionEventSetting.motionEventLandNextImp(curl)
            } else {
                MotionEventSetting.motionEventPortNextImp(curl)
            }
        }
        curlCurrentIndex = curl.currentIndex
    }

    fun curlViewLayoutSetNext4PageMotionEvent(curl: CurlView, view: View) {
        curlLiveData?.postValue(curl)
        view.isEnabled = false
        viewModelScope.launch(Dispatchers.Default) {
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            view.isEnabled = true
        }
        Timber.d("currentIndex1:${curl.currentIndex}")
        curlCurrentIndex = curl.currentIndex - 2
    }

    fun curlViewLayoutSetBack4PageMotionEvent(curl: CurlView, view: View) {
        curlLiveData?.postValue(curl)
        view.isEnabled = false
        viewModelScope.launch(Dispatchers.Default) {
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            view.isEnabled = true
        }
        Timber.d("currentIndex2:${curl.currentIndex}")
        curlCurrentIndex = curl.currentIndex - 2
    }

    fun isCommandButtonOpen() {
        isCommandButtonOpen = !isCommandButtonOpen
    }

    private fun Int.getContextString(): String {
        return getApplication<MyApplication>().applicationContext.getString(this)
    }

    private fun Int.getContextDrawable(): Drawable? {
        return getApplication<MyApplication>().applicationContext.getDrawable(this)
    }
}
