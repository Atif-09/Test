package com.example.virtoxedtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter(val employee: String?): RecyclerView.Adapter<Myadapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.employeeName.text = employee?.get(position).toString()
    }

    override fun getItemCount(): Int {
        return employee?.length ?: 0

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val employeeName = itemView.findViewById<TextView>(R.id.employee_name)

    }

}


