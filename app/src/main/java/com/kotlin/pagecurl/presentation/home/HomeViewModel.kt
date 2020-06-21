package com.kotlin.pagecurl.presentation.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.data.entity.UserDetail
import com.kotlin.pagecurl.data.repository.ProjectRepository
import com.kotlin.pagecurl.domainobject.model.Person
import com.kotlin.pagecurl.domainobject.model.getSuperheroList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel(), LifecycleObserver {

    private val userList: MutableLiveData<List<UserDetail>> = MutableLiveData()

    val superheroes: LiveData<List<Person>> = liveData {
        val superheroList = loadSuperheroes()
        emit(superheroList)
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            projectRepository.fetchUserList(12).runCatching {
                userList.postValue(data)
            }
        }
    }

    suspend fun loadSuperheroes(): List<Person> {
        delay(500)
        return getSuperheroList()
    }
}
