package com.kotlin.pagecurl.presentation.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.kotlin.pagecurl.api.UserQuery
import com.kotlin.pagecurl.data.entity.UserDetail
import com.kotlin.pagecurl.data.repository.ProjectRepository
import com.kotlin.pagecurl.domainobject.model.Person
import com.kotlin.pagecurl.domainobject.model.getSuperheroList
import com.kotlin.pagecurl.presentation.ApolloController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
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
        val viewerQuery = UserQuery.builder().build()
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.setupApollo().query(viewerQuery)
                .enqueue(object : ApolloCall.Callback<UserQuery.Data>() {
                    override fun onFailure(e: ApolloException) {
                        // Failure
                        Timber.d("check_testResponse_Failure:$e")
                    }

                    override fun onResponse(response: Response<UserQuery.Data>) {
                        // Sucess
                        Timber.d("check_testResponse:${response.data}")
                    }
                })
        }
    }

    suspend fun loadSuperheroes(): List<Person> {
        delay(500)
        return getSuperheroList()
    }
}
