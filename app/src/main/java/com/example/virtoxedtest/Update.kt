package com.example.virtoxedtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.virtoxedtest.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener {
            val employeeData = EmployeeData(
                id = intent.getIntExtra("id", -1),
                name = binding.editName.text.toString(),
                email = binding.editEmail.text.toString(),
                phone = binding.editPhone.text.toString(),
                salary = binding.editSalary.text.toString(),
                designation = binding.editDesignation.text.toString(),
            )
            val repository = EmployeeRepsitory(application)
            repository.update(employeeData)
            finish()
        }
        binding.cancel.setOnClickListener {
            clearData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val repository = EmployeeRepsitory(application)
        val employee = repository.getEmployeeData(intent.getIntExtra("id", -1))

        binding.editName.setText(employee?.name)
        binding.editEmail.setText(employee?.email)
        binding.editPhone.setText(employee?.phone)
        binding.editSalary.setText(employee?.salary)
        binding.editDesignation.setText(employee?.designation)

    }
    private fun clearData() {
        binding.editName.setText("")
        binding.editEmail.setText("")
        binding.editPhone.setText("")
        binding.editSalary.setText("")
        binding.editDesignation.setText("")
    }
}