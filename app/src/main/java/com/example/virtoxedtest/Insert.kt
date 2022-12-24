package com.example.virtoxedtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.virtoxedtest.databinding.ActivityInsertBinding

class Insert : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insert.setOnClickListener {
            saveData()
        }
        binding.cancel.setOnClickListener {
            clearData()
            val intent = Intent(this, EmployeeDetails::class.java)
            startActivity(intent)
        }
    }
    private fun saveData(){
        val insertName = binding.editName.text.toString()
        val insertEmail = binding.editEmail.text.toString()
        val insertPhone = binding.editPhone.text.toString()
        val insertSalary = binding.editSalary.text.toString()
        val insertDesignation = binding.editDesignation.text.toString()

        val sharedPref = getSharedPreferences("Virtoxed", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()
        editor.putString("name", insertName)
        editor.putString("email", insertEmail)
        editor.putString("phone", insertPhone)
        editor.putString("salary", insertSalary)
        editor.putString("designation", insertDesignation)
        editor.apply()

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
    }
    private fun clearData(){
        binding.editName.setText("")
        binding.editEmail.setText("")
        binding.editPhone.setText("")
        binding.editSalary.setText("")
        binding.editDesignation.setText("")
    }

}