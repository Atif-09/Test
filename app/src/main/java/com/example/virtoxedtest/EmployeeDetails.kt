package com.example.virtoxedtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.virtoxedtest.databinding.ActivityEmployeeDetailsBinding

class EmployeeDetails : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener {
            val intent = Intent(this, Update::class.java)
        }
    }
}