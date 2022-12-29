package com.ahmed.khabertask.data.model

data class LoginResponse(
    val AccountId: Int? = 0,
    val Activation: Boolean? = false,
    val Token: String? = "",
    val success: Boolean? = false,
    val EnglishMessage: String? = "",
    val ArabicMessage: String? = "",
    val userType: Int? = 0,
    val user: User?
)