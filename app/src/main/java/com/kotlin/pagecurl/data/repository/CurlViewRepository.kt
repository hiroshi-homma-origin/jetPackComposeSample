package com.kotlin.pagecurl.data.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.pagecurl.api.fragment.Pokemon

class CurlViewRepository {
    companion object {
        var pokemonLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
    }
}
