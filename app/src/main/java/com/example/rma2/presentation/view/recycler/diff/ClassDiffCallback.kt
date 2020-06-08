package com.example.rma2.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma2.data.models.Class

class ClassDiffCallback : DiffUtil.ItemCallback<Class>(){
    override fun areItemsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.dan == newItem.dan &&
                oldItem.grupe == newItem.grupe &&
                oldItem.nastavnik == newItem.nastavnik &&
                oldItem.predmet == newItem.predmet &&
                oldItem.termin == newItem.termin &&
                oldItem.tip == newItem.tip &&
                oldItem.ucionica == newItem.ucionica
    }

}