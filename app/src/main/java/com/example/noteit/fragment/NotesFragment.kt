package com.example.noteit.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.noteit.R
import com.example.noteit.database.NotesDatabase
import com.example.noteit.databinding.NotesFragmentBinding
import com.example.noteit.model.NotesData
import java.text.SimpleDateFormat
import java.util.*


class NotesFragment(title: String?, noteTxt: String?, private val id: Int?) : Fragment() {

    private var binding: NotesFragmentBinding? = null
    var currentDate:String? = null
    private val titleForUpdate = title
    private val noteTxtForUpdate = noteTxt

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NotesFragmentBinding.inflate(inflater,container,false)

        if(!TextUtils.isEmpty(titleForUpdate))
            binding!!.titleEditTxt.setText(titleForUpdate)

        if(!TextUtils.isEmpty(noteTxtForUpdate))
            binding!!.notesEditTxt.setText(noteTxtForUpdate)


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")

        currentDate = sdf.format(Date())

        val view: View = requireActivity().findViewById(R.id.back_img)
        view.visibility = View.VISIBLE

        view.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        binding?.saveBtn?.setOnClickListener{

            val titleTxt: String? = binding?.titleEditTxt?.text.toString().trim()
            val notesTxt: String? = binding?.notesEditTxt?.text.toString().trim()


            when {
                TextUtils.isEmpty(titleTxt) -> {
                    Toast.makeText(requireContext(),"Title Text can't be empty..",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(notesTxt) -> {
                    Toast.makeText(requireContext(),"Notes can't be empty..",Toast.LENGTH_SHORT).show()
                }
                else -> {

                    try {
                        if (id != null) {
                            NotesDatabase.getDatabase(requireContext()).noteDao()
                                .updateNote(titleTxt!!,currentDate!!,notesTxt!!,id)
                        } else {
                            val notesEntity = NotesData()
                            notesEntity.title = titleTxt
                            notesEntity.noteText = notesTxt
                            notesEntity.dateTime = currentDate
                            NotesDatabase.getDatabase(requireContext()).noteDao()
                                .insertNotes(notesEntity)
                        }
                        requireActivity().supportFragmentManager.popBackStack()
                    }catch (e: Exception){
                        e.printStackTrace()
                    }


                }
            }


        }
    }
}