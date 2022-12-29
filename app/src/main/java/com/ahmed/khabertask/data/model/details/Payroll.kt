package com.ahmed.khabertask.data.model.details

data class Payroll(
    val Employee: List<EmployeeItem>?,
    val Allowences: List<AllowencesItem>?,
    val Deduction: List<DeductionItem>?,
    val Date: String? = ""
)