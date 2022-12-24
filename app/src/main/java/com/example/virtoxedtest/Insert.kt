package com.example.virtoxedtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.virtoxedtest.databinding.ActivityInsertBinding
import kotlin.random.Random

class Insert : AppCompatActivity() {


    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insert.setOnClickListener {
            saveData()
        }
        binding.cancel.setOnClickListener {
            clearData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {
        val insertName = binding.editName.text.toString()
        val insertEmail = binding.editEmail.text.toString()
        val insertPhone = binding.editPhone.text.toString()
        val insertSalary = binding.editSalary.text.toString()
        val insertDesignation = binding.editDesignation.text.toString()

        val number = Random.nextInt(1000000)


        val employeeData = EmployeeData(
            id = number,
            name = insertName,
            email = insertEmail,
            phone = insertPhone,
            salary = insertSalary,
            designation = insertDesignation
        )
        val repsitory = EmployeeRepsitory(application)
        repsitory.insert(employeeData)

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun clearData() {
        binding.editName.setText("")
        binding.editEmail.setText("")
        binding.editPhone.setText("")
        binding.editSalary.setText("")
        binding.editDesignation.setText("")
    }

}