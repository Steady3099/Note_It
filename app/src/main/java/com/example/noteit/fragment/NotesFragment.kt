package com.example.noteit.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteit.databinding.NotesFragmentBinding



class NotesFragment : Fragment() {

    private var binding: NotesFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NotesFragmentBinding.inflate(inflater,container,false)

        binding!!.editor.setEditorHeight(200)

        binding!!.editor.setEditorHeight(200)
        binding!!.editor.setEditorFontSize(22)
        binding!!.editor.setEditorFontColor(Color.BLACK)
        binding!!.editor.setPadding(10, 10, 10, 10)
        binding!!.editor.setPlaceholder("Insert text here...")
        binding!!.editor.focusEditor()
        binding!!.editor.setInputEnabled(true)


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.editor.setOnTextChangeListener {
            @Override
            fun onTextChange(text: String) {
                binding!!.preview.text = text
            }

        }

        binding!!.actionBold.setOnClickListener{
            binding!!.editor.setBold()
        }

        binding!!.actionRedo.setOnClickListener{
            binding!!.editor.redo()
        }

        binding!!.actionUndo.setOnClickListener{
            binding!!.editor.undo()
        }

        binding!!.actionItalic.setOnClickListener{
            binding!!.editor.setItalic()
        }

        binding!!.actionStrikethrough.setOnClickListener{
            binding!!.editor.setStrikeThrough()
        }

        binding!!.actionUnderline.setOnClickListener{
            binding!!.editor.setUnderline()
        }

        binding!!.actionTxtColor.setOnClickListener{
            binding!!.editor.insertTodo()
        }

    }
}