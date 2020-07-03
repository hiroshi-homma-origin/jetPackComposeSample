package com.kotlin.pagecurl.data.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.domainobject.model.User

class CurlViewRepository {
    companion object {
        var pokemonLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
        var userLiveData: MutableLiveData<List<User>> = MutableLiveData()
    }
}
