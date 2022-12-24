package com.example.virtoxedtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class EmployeeRepsitory(appContext: Application) {

    private val employeeData = "employee_data"
    private val gson = Gson()
    private val sharedPref: SharedPreferences =
        appContext.getSharedPreferences("Virtoxed", Context.MODE_PRIVATE)

    fun insert(data: EmployeeData) {
        val getEmployeeString = sharedPref.getString(employeeData, "")

        val employeeList = mutableListOf<EmployeeData>()

        val employeeDataFromSharedPreferences =
            gson.fromJson(getEmployeeString, Array<EmployeeData>::class.java)

        if (employeeDataFromSharedPreferences != null)
            employeeList.addAll(employeeDataFromSharedPreferences)

        employeeList.add(data)

        val jsonString = gson.toJson(employeeList)
        sharedPref.edit().putString(employeeData, jsonString).apply()
    }

    fun getEmployeeList(): Array<EmployeeData>? {
        val getEmployeeString = sharedPref.getString(employeeData, "")
        return gson.fromJson(getEmployeeString, Array<EmployeeData>::class.java)

    }

    fun update(data: EmployeeData) {
        val employeeList = mutableListOf<EmployeeData>()
        val getEmployeeString = sharedPref.getString(employeeData, "")
        val sharedPreferencesEmployeeList =
            gson.fromJson(getEmployeeString, Array<EmployeeData>::class.java)

        employeeList.addAll(sharedPreferencesEmployeeList.filter { it.id != data.id })

        employeeList.add(data)
        val jsonString = gson.toJson(employeeList)
        sharedPref.edit().putString(employeeData, jsonString).apply()
    }

    fun getEmployeeData(id: Int): EmployeeData? {
        val getEmployeeString = sharedPref.getString("employee_data", "")
        val employeeList = gson.fromJson(getEmployeeString, Array<EmployeeData>::class.java)
        return employeeList.find { it.id == id }
    }

    fun insert(data: MutableList<EmployeeData>) {
        val getEmployeeString = sharedPref.getString(employeeData, "")

        val employeeList = mutableListOf<EmployeeData>()

        val employeeDataFromSharedPreferences =
            gson.fromJson(getEmployeeString, Array<EmployeeData>::class.java)

        if (employeeDataFromSharedPreferences != null)
            employeeList.addAll(employeeDataFromSharedPreferences)

        employeeList.addAll(data)

        val jsonString = gson.toJson(employeeList)
        sharedPref.edit().putString(employeeData, jsonString).apply()
    }
}


