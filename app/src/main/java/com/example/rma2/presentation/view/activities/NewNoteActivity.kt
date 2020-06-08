package com.example.rma2.presentation.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.rma2.R
import com.example.rma2.data.models.NoteEntity
import com.example.rma2.presentation.contract.ClassContract
import com.example.rma2.presentation.contract.NoteContract
import com.example.rma2.presentation.viewmodel.ClassViewModel
import com.example.rma2.presentation.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.item_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class NewNoteActivity: AppCompatActivity(R.layout.activity_new_note) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNewNote.setOnClickListener {
            val title = titleNewNote.text.toString()
            val content = contentNewNote.text.toString()
            val archived = 0
            if(title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            noteViewModel.insert(NoteEntity(id = 0, archived = archived, content = content, title = title, date = Date()))
            //Toast.makeText(this, title+ " " + content, Toast.LENGTH_SHORT).show()
            finish()
        }

        cancelNewNote.setOnClickListener {
            finish()
        }

    }



}