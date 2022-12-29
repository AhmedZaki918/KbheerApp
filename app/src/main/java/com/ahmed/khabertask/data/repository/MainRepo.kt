package com.ahmed.khabertask.data.repository

import com.ahmed.khabertask.data.model.LoginRequest
import com.ahmed.khabertask.data.network.APIService
import com.ahmed.khabertask.data.network.SafeApiCall
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: APIService
) : SafeApiCall {

    suspend fun loginUser(loginRequest: LoginRequest) = safeApiCall {
        api.login(loginRequest)
    }

}