package com.example.rma2.presentation.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma2.R
import com.example.rma2.presentation.contract.NoteContract
import com.example.rma2.presentation.view.activities.NewNoteActivity
import com.example.rma2.presentation.view.recycler.adapter.NoteAdapter
import com.example.rma2.presentation.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.lifecycle.Observer


class NotesFragment : Fragment(R.layout.fragment_notes){


    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    private lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecycler()
        initObservers()
    }


    fun initListeners(){
        btnAddNew.setOnClickListener {
            val intent = Intent(context, NewNoteActivity::class.java)
            startActivity(intent)
        }

        noteSearch.doAfterTextChanged {
            noteViewModel.getNotesByTitleOrContent(it.toString())

            if (simpleSwitch.isChecked){
                noteViewModel.getByArchived(1)
            }else{
                noteViewModel.getByArchived(0)
            }

        }



    }

    private fun initRecycler() {
        notesLst.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(
            {
                noteViewModel.deleteById(it.id)
                Toast.makeText(context,"Obrisana beleska sa naslovom" + it.title, Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(context,"edit", Toast.LENGTH_SHORT).show()
            },{
                noteViewModel.getByIdAndArchiveOrUnarchive(it.id)
                Toast.makeText(context,"Uspesno arhiviran note sa naslovom" + it.title, Toast.LENGTH_SHORT).show()
            },{
                noteViewModel.getByIdAndArchiveOrUnarchive(it.id)
                Toast.makeText(context,"Uspesno povracen iz arhive note sa naslovom" + it.title, Toast.LENGTH_SHORT).show()
            }
        )
        notesLst.adapter = adapter
    }

    private fun initObservers() {
        noteViewModel.notes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        noteViewModel.getAll()

    }




}