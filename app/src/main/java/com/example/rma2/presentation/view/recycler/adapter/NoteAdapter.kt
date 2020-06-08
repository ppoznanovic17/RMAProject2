package com.example.rma2.presentation.view.recycler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rma2.R
import com.example.rma2.data.models.Note
import com.example.rma2.presentation.view.recycler.diff.NoteDiffCallback
import com.example.rma2.presentation.view.recycler.viewholder.NoteListViewHolder

class NoteAdapter(private val deleteBtn: (Note) -> Unit,
                  private val editBtn: (Note) -> Unit,
                  private val archiveBtn: (Note) -> Unit,
                  private val unarchiveBtn: (Note) -> Unit)
                    : ListAdapter <Note, NoteListViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteListViewHolder(containerView,{
                val note = getItem(it)
                deleteBtn.invoke(note)
             },
            {
                val note = getItem(it)
                editBtn.invoke(note)
            },
            {
                val note = getItem(it)
                archiveBtn.invoke(note)
            },
            {
                val note = getItem(it)
                unarchiveBtn.invoke(note)
            })
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }


}