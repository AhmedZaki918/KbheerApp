package com.ahmed.khabertask.data.network

import com.ahmed.khabertask.data.model.details.DetailsResponse
import com.ahmed.khabertask.data.model.login.LoginRequest
import com.ahmed.khabertask.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIService {

    @POST("LogIn")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("GetPayroll")
    suspend fun getUser(
        @Header("Authorization") token: String
     ): DetailsResponse
}