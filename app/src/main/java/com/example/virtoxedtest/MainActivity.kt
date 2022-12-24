package com.example.virtoxedtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virtoxedtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val intent = Intent(this, Insert::class.java)
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("Virtoxed", Context.MODE_PRIVATE)
        val employeeNames = sharedPref.getString("name", null)

        binding.employeeNames.adapter = Myadapter(employeeNames)
        binding.employeeNames.layoutManager = LinearLayoutManager(this)

    }


}