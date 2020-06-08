package com.example.rma2.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma2.data.models.Note

class NoteDiffCallback: DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == oldItem.title &&
                oldItem.content == newItem.content &&
                oldItem.archives == newItem.archives
    }

}