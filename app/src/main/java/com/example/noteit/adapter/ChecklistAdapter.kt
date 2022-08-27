package com.example.noteit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.noteit.databinding.ChecklistCardBinding
import com.example.noteit.model.People

class ChecklistAdapter : RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {
    private var items: List<People> = ArrayList()


    inner class ViewHolder(binding: ChecklistCardBinding) : RecyclerView.ViewHolder(binding.root) {

        var editText: EditText? = null
        var checkBox : CheckBox? = null

        init {
            editText = binding.editText
            checkBox = binding.checkbox
        }


    }

    fun addNewItem() {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChecklistCardBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.checkBox?.setOnClickListener{

            if(holder.checkBox!!.isChecked){
                holder.checkBox!!.isChecked = false
                holder.editText?.isEnabled = true
            }else{
                holder.checkBox!!.isChecked = true
                holder.editText?.isEnabled = false
            }


        }


    }
}