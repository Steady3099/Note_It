package com.example.noteit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteit.R
import com.example.noteit.adapter.HomeAdapter
import com.example.noteit.databinding.HomeScreenFragmentBinding
import com.example.noteit.model.People
import com.example.noteit.utils.ViewAnimation


class HomeFragment : Fragment() {

    private var binding: HomeScreenFragmentBinding? = null
    private var rotate = false
    private var mAdapter: HomeAdapter? = null
    private var items: MutableList<People> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var people  = People()

        people.date = "30-June,1999"
        people.headerTxt = "Personal Note"
        people.infoTxt = "Note it down...."
        people.isNotes = false
        people.sectionName = "Notes"
        people.isSection = false
        items.add(people)



        binding = HomeScreenFragmentBinding.inflate(inflater,container,false)
        ViewAnimation.showOut(binding!!.llNotes)
        ViewAnimation.showOut(binding!!.llChecklist)
        binding!!.backDrop.visibility = View.GONE
        rotate = false

        if(items != null && items.isNotEmpty()) {
            binding!!.recyclerView!!.layoutManager = LinearLayoutManager(context)
            mAdapter = context?.let { HomeAdapter(it, items) }
            binding!!.recyclerView!!.adapter = mAdapter
        }else{
            binding!!.recyclerView.visibility = View.GONE
            binding!!.noRecordImg.visibility = View.VISIBLE
            binding!!.noRecordTxt.visibility = View.VISIBLE
        }

        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.fabAdd.setOnClickListener {
            rotate = ViewAnimation.rotateFab(it , !rotate)
            if (rotate) {
                ViewAnimation.showIn(binding!!.llNotes)
                ViewAnimation.showIn(binding!!.llChecklist)
                binding!!.backDrop.visibility = View.VISIBLE
            } else {
                ViewAnimation.showOut(binding!!.llNotes)
                ViewAnimation.showOut(binding!!.llChecklist)
                binding!!.backDrop.visibility = View.GONE
            }
        }

        binding!!.backDrop.setOnClickListener {
            rotate = ViewAnimation.rotateFab(binding!!.fabAdd, false)
            ViewAnimation.showOut(binding!!.llNotes)
            ViewAnimation.showOut(binding!!.llChecklist)
            binding!!.backDrop.visibility = View.GONE
        }

        binding!!.llNotes.setOnClickListener {
            val notesFragment = NotesFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame,notesFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        binding!!.llChecklist.setOnClickListener {
            val checklistFragment = ChecklistFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame,checklistFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }


    }
}