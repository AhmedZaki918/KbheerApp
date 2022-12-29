package com.ahmed.khabertask.ui.home

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.khabertask.data.local.Constants.TOKEN_KEY
import com.ahmed.khabertask.data.model.login.LoginRequest
import com.ahmed.khabertask.data.model.login.LoginResponse
import com.ahmed.khabertask.data.network.Resource
import com.ahmed.khabertask.data.repository.MainRepo
import com.ahmed.khabertask.util.DataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo,
    private val dataStore: DataStoreRepo
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>> = _loginResponse
    val token = MutableLiveData<String>()

    init {
        retrieveToken(TOKEN_KEY)
    }


    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginResponse.value = repo.loginUser(loginRequest)
        }
    }

    fun saveToken(key: Preferences.Key<String>, value: String) {
        viewModelScope.launch {
            dataStore.writeString(key, value)
        }
    }

    private fun retrieveToken(key: Preferences.Key<String>) {
        viewModelScope.launch {
            dataStore.readString(key).collectLatest {
                token.value = it
            }
        }
    }
}