package com.example.rma2.presentation.view.recycler.viewholder

import androidx.core.view.isVisible
import com.example.rma2.data.models.Note
import kotlinx.android.synthetic.main.item_note.view.*



import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma2.data.models.Class
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_class_list.view.*

class NoteListViewHolder(override val containerView: View,
                         private val deleteBtn: (Int) -> Unit,
                         private val editBtn: (Int) -> Unit,
                         private val archiveBtn: (Int) -> Unit,
                         private val unarchiveBtn: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    init {
        containerView.deleteNote.setOnClickListener {
            deleteBtn.invoke(adapterPosition)
        }
        containerView.editNote.setOnClickListener {
            editBtn.invoke(adapterPosition)
        }

        containerView.archiveNote.setOnClickListener {
            archiveBtn.invoke(adapterPosition)
        }
        containerView.unarchiveNote.setOnClickListener {
            unarchiveBtn.invoke(adapterPosition)
        }
    }


    fun bind(note: Note){

        containerView.titleNote.text = note.title
        containerView.contentNote.text = note.content
        if(note.archives == 1){
            containerView.unarchiveNote.isVisible = true
            containerView.archiveNote.isVisible = false
        }else{
            containerView.unarchiveNote.isVisible = false
            containerView.archiveNote.isVisible = true
        }

    }

}