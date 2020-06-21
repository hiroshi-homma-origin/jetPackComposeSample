package com.kotlin.pagecurl.presentation.curlViewer

import android.content.res.Configuration
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.data.repository.ProjectRepository
import com.kotlin.pagecurl.domainobject.usecase.MotionEventSetting
import com.kotlin.pagecurl.viewExt.curl.base.CurlView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurlViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel(), LifecycleObserver {

    val curlLiveData: MutableLiveData<CurlView> ? = null

    fun curlViewLayoutSetBackMotionEvent(curl: CurlView, orientation: Int) {
        curlLiveData?.postValue(curl)
        viewModelScope.launch(Dispatchers.Default) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                MotionEventSetting.motionEventLandBackImp(curl)
            } else {
                MotionEventSetting.motionEventPortBackImp(curl)
            }
        }
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
    }

    fun curlViewLayoutSetNext4PageMotionEvent(curl: CurlView, it: View) {
        curlLiveData?.postValue(curl)
        it.isEnabled = false
        viewModelScope.launch(Dispatchers.Default) {
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortNextImp(curl)
            it.isEnabled = true
        }
    }

    fun curlViewLayoutSetBack4PageMotionEvent(curl: CurlView, it: View) {
        curlLiveData?.postValue(curl)
        it.isEnabled = false
        viewModelScope.launch(Dispatchers.Default) {
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            delay(110L)
            MotionEventSetting.motionEventPortBackImp(curl)
            it.isEnabled = true
        }
    }
}
