package com.example.noteit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteit.adapter.ChecklistAdapter
import com.example.noteit.adapter.HomeAdapter
import com.example.noteit.databinding.ChecklistFragmentBinding

class ChecklistFragment : Fragment() {

    private var binding: ChecklistFragmentBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ChecklistFragmentBinding.inflate(inflater,container,false)

        return binding!!.root
    }


}