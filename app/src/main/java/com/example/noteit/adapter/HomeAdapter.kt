package com.example.noteit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteit.R
import com.example.noteit.databinding.HomeScreenCardsBinding
import com.example.noteit.databinding.NoteChecklistSectionBinding
import com.example.noteit.model.People

class HomeAdapter(context: Context, items: List<People>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<People> = ArrayList()
    private val VIEW_ITEM = 1
    private val VIEW_SECTION = 0
    private var ctx: Context? = null


    init {
        this.items = items
        this.ctx = context
    }


    inner class OriginalViewHolder(binding: HomeScreenCardsBinding) : RecyclerView.ViewHolder(binding.root) {
        var img: ImageView? = null
        var headerTxt: TextView? = null
        var infoTxt: TextView? = null
        var dateTxt: TextView? = null


        init {
            img = binding.notesImg
            headerTxt = binding.txtHeading
            infoTxt = binding.txtInfo
            dateTxt = binding.txtDate
        }

    }

    inner class SectionViewHolder(binding: NoteChecklistSectionBinding) : RecyclerView.ViewHolder(binding.root) {
        var titleSection: TextView? = null

        init {
            titleSection = binding.sectionTxt
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_ITEM) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HomeScreenCardsBinding.inflate(inflater,parent,false)
            OriginalViewHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NoteChecklistSectionBinding.inflate(inflater,parent,false)
            SectionViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val p = items[position]
        if (holder is OriginalViewHolder) {

            if(p.isNotes)
                holder.img?.setImageResource(R.drawable.notes_icon)
            else
                holder.img?.setImageResource(R.drawable.checklist_icon)

            holder.headerTxt?.text = p.headerTxt
            holder.infoTxt?.text = p.infoTxt
            holder.dateTxt?.text = p.date

        } else {
            val view = holder as SectionViewHolder
            view.titleSection?.text = p.sectionName
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].section) VIEW_SECTION else VIEW_ITEM
    }

    override fun getItemCount(): Int {
        return items.size
    }

}