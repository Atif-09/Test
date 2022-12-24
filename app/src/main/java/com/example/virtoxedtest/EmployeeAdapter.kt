package com.example.virtoxedtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

    private val employeeList = mutableListOf<EmployeeData>()

    fun notifyDataChanged(list: List<EmployeeData>) {
        employeeList.clear()
        employeeList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val employeeData = employeeList[position]
        holder.employeeName.text = employeeData.name
        holder.employeeName.setOnClickListener {
            onItemClick.onItemClicked(employeeData.id)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employee_name)
    }

}

interface OnItemClick {
    fun onItemClicked(id: Int)
}


