package com.example.virtoxedtest

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virtoxedtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityMainBinding
    val adapter = EmployeeAdapter(this)
    private val employeeList = mutableListOf<EmployeeData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val intent = Intent(this, Insert::class.java)
            startActivity(intent)
        }

        binding.sync.setOnClickListener {
            checkForPermission()
        }


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty().not())
                    adapter.notifyDataChanged(employeeList.filter { it.name.contains(query!!) })
                else
                    adapter.notifyDataChanged(employeeList)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty().not())
                    adapter.notifyDataChanged(employeeList.filter { it.name.contains(newText!!) })
                else
                    adapter.notifyDataChanged(employeeList)
                return false
            }

        })

        binding.search.setOnCloseListener {
            adapter.notifyDataChanged(employeeList)
            return@setOnCloseListener false
        }
    }

    override fun onResume() {
        super.onResume()

        refreshData()

    }

    private fun refreshData() {
        val repository = EmployeeRepsitory(application)
        employeeList.clear()
        employeeList.addAll(repository.getEmployeeList()?.toList() ?: emptyList())
        adapter.notifyDataChanged(employeeList)

        binding.employeeNames.adapter = adapter
        binding.employeeNames.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClicked(id: Int) {
        val intent = Intent(this, EmployeeDetails::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


    private fun getContactList(): MutableList<EmployeeData> {

        val employeList = mutableListOf<EmployeeData>()
        val cr = contentResolver
        val cur: Cursor? = cr.query(
            ContactsContract.Contacts.CONTENT_URI, null, null, null, null
        )
        if ((cur?.count ?: 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                val id: String = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name: String = cur.getString(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
                if (cur.getInt(
                        cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER
                        )
                    ) > 0
                ) {
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    while (pCur?.moveToNext() == true) {
                        val phoneNo: String? = pCur?.getString(
                            pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                        )
                        Log.i(TAG, "Name: $name")
                        Log.i(TAG, "Phone Number: $phoneNo")

                        phoneNo?.let {
                            employeList.add(
                                EmployeeData(
                                    name = name, phone = phoneNo
                                )
                            )
                        }
                    }
                    pCur?.close()
                }
            }
        }
        cur?.close()

        return employeList
    }

    private fun checkForPermission() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_CONTACTS
                )
            ) {
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(
                    this, Array(1) { Manifest.permission.READ_CONTACTS }, 1111
                );
            }
        } else {
            val repository = EmployeeRepsitory(application)
            val contactList = getContactList()
            repository.insert(contactList)
            refreshData()
        }
    }

}