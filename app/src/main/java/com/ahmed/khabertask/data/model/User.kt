package com.ahmed.khabertask.data.model

data class User(
    val PhoneNumberConfirmed: Boolean? = false,
    val PatientImage: String? = "",
    val HasInsurance: Boolean? = false,
    val ClassId: Int? = 0,
    val ClassArabicName: String? = "",
    val LastNameAr: String? = "",
    val Gender: Int? = 0,
    val EmailConfirmed: Boolean? = false,
    val MobileNumber: String? = "",
    val FirstNameAr: String? = "",
    val LastNameEn: String? = "",
    val ClassEnglishName: String? = "",
    val AspNetUsersId: Int? = 0,
    val Id: Int? = 0,
    val FirstNameEn: String? = "",
    val BirthDate: String? = ""
)