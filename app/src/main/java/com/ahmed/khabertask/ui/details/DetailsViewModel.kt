package com.ahmed.khabertask.ui.details

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.khabertask.data.local.Constants.TOKEN_KEY
import com.ahmed.khabertask.data.model.details.DetailsResponse
import com.ahmed.khabertask.data.network.Resource
import com.ahmed.khabertask.data.repository.MainRepo
import com.ahmed.khabertask.util.DataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: MainRepo,
    private val dataStore: DataStoreRepo
) : ViewModel() {

    private val _userResponse = MutableLiveData<Resource<DetailsResponse>>()
    val userResponse: LiveData<Resource<DetailsResponse>> = _userResponse
    private val token = MutableLiveData<String>()



    init {
        retrieveToken(TOKEN_KEY)
        getUser()
    }

    private fun retrieveToken(key: Preferences.Key<String>) {
        viewModelScope.launch {
            dataStore.readString(key).collectLatest {
                token.value = it
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            _userResponse.value = repo.getUserDetails("Bearer ${token.value}")
        }
    }
}