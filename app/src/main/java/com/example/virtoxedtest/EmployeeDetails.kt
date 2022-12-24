package com.example.virtoxedtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.virtoxedtest.databinding.ActivityEmployeeDetailsBinding

class EmployeeDetails : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener {
            val updateIntent = Intent(this, Update::class.java)
            val id = intent.getIntExtra("id", -1)
            updateIntent.putExtra("id", id)
            startActivity(updateIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        val id = intent.getIntExtra("id", -1)
        val employeeRepository = EmployeeRepsitory(application)

        val employeeData = employeeRepository.getEmployeeData(id)

        binding.name.text = employeeData?.name
        binding.email.text = employeeData?.email
        binding.designation.text = employeeData?.designation
        binding.phone.text = employeeData?.phone
        binding.salary.text = employeeData?.salary
    }
}
