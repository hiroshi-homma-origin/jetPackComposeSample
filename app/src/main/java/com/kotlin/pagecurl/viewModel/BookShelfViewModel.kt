package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.data.repository.CurlViewRepository.Companion.userLiveData
import com.kotlin.pagecurl.domainobject.model.userListSizeFirst
import com.kotlin.pagecurl.domainobject.model.users

class BookShelfViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    fun fetchData() {
        userLiveData.postValue(users.filter { it.id <= userListSizeFirst })
    }

    fun getData() = userLiveData

    fun retryGetData() {
//        val listSize = userListSize
//        userLiveData.postValue(users.filter { it.id < listSize })
    }

    fun getLastIndexData() = users[users.lastIndex]

    private fun Int.getContextString(): String {
        return getApplication<MyApplication>().applicationContext.getString(this)
    }

    private fun Int.getContextDrawable(): Drawable? {
        return getApplication<MyApplication>().applicationContext.getDrawable(this)
    }
}
