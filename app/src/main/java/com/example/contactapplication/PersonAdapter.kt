package com.example.contactapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PersonAdapter(var activity: Activity, var data: MutableList<Person>) :
    RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tname = itemView.findViewById<TextView>(R.id.tv_name)
        val tnumber = itemView.findViewById<TextView>(R.id.tv_number)
        val taddress = itemView.findViewById<TextView>(R.id.tv_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.custom_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tname.text = data[position].name
        holder.tnumber.text = data[position].number
        holder.taddress.text = data[position].address


    }
}