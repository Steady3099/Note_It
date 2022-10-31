package com.example.noteit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteit.R
import com.example.noteit.RefreshListener
import com.example.noteit.adapter.HomeAdapter
import com.example.noteit.adapter.NotesAdapter
import com.example.noteit.database.NotesDatabase
import com.example.noteit.databinding.HomeScreenFragmentBinding
import com.example.noteit.model.NotesData
import com.example.noteit.model.People


class HomeFragment : Fragment(),RefreshListener {

    private var binding: HomeScreenFragmentBinding? = null
    private var mAdapter: NotesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)

        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val view: View = requireActivity().findViewById(R.id.back_img)
        view.visibility = View.GONE

        binding!!.fabAdd.setOnClickListener {

            val notesFragment = NotesFragment(null,null,null)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )

            transaction.replace(R.id.frame, notesFragment).addToBackStack(null).commit()

        }

    }

    override fun onResume() {
        super.onResume()

        refresh()

    }

    override fun refresh() {

        var notesData: MutableList<NotesData>? = NotesDatabase.getDatabase(requireContext()).noteDao().getAllNotes()

        if(notesData != null && notesData.isNotEmpty()){
            binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
            mAdapter = context?.let { NotesAdapter(notesData,requireContext(),this) }
            binding!!.recyclerView.adapter = mAdapter
        }else {
            binding!!.recyclerView.visibility = View.GONE
            binding!!.noRecordImg.visibility = View.VISIBLE
            binding!!.noRecordTxt.visibility = View.VISIBLE
        }

    }

}