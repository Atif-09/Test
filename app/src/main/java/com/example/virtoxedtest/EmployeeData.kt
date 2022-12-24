package com.example.virtoxedtest

import kotlin.random.Random

data class EmployeeData(
    val id: Int = Random.nextInt(1000000),
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val salary: String = "",
    val designation: String = ""
)
