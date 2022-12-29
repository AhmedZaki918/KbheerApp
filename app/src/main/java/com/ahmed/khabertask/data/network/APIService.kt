package com.ahmed.khabertask.data.network

import com.ahmed.khabertask.data.model.LoginRequest
import com.ahmed.khabertask.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("LogIn")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

}