package com.ahmed.khabertask.data.model.details

data class DetailsResponse(
    val Payroll: Payroll?,
    val EnglishMessage: String? = "",
    val ArabicMessage: String? = "",
    val Activation: Boolean? = false,
    val Success: Boolean? = false
)