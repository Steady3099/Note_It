package com.example.noteit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.noteit.R
import com.example.noteit.RefreshListener
import com.example.noteit.database.NotesDatabase
import com.example.noteit.databinding.HomeScreenCardsBinding
import com.example.noteit.fragment.NotesFragment
import com.example.noteit.model.NotesData

class NotesAdapter(items: MutableList<NotesData>,context: Context,iRefreshListener: RefreshListener) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var data: MutableList<NotesData> = items
    private val mContext: Context = context
    private val refreshListener = iRefreshListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeScreenCardsBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding)
    }

    inner class NotesViewHolder(binding: HomeScreenCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var img: ImageView? = null
        var headerTxt: TextView? = null
        var infoTxt: TextView? = null
        var dateTxt: TextView? = null
        var editImg: ImageView? = null
        var deleteImg: ImageView? = null


        init {
            img = binding.notesImg
            headerTxt = binding.txtHeading
            infoTxt = binding.txtInfo
            dateTxt = binding.txtDate
            editImg = binding.editImg
            deleteImg = binding.deleteImg
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val p = data[position]
        holder.img?.setImageResource(R.drawable.notes_icon)
        holder.headerTxt?.text = p.title
        holder.infoTxt?.text = p.noteText
        holder.dateTxt?.text = p.dateTime

        holder.deleteImg?.setOnClickListener{
            NotesDatabase.getDatabase(mContext).noteDao().deleteSpecificNote(data[holder.adapterPosition].id!!)
            data.remove(data[holder.adapterPosition])
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition,data.size)

            if(data.size == 0) {
                refreshListener.refresh()
            }

        }

        holder.editImg?.setOnClickListener{
            val notesData: NotesData = NotesDatabase.getDatabase(mContext).noteDao().getSpecificNote(data[holder.adapterPosition].id!!)

            val notesFragment = NotesFragment(notesData.title,notesData.noteText,notesData.id)
            val transaction = (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()

            transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )

            transaction.replace(R.id.frame, notesFragment).addToBackStack(null).commit()

        }


    }
}