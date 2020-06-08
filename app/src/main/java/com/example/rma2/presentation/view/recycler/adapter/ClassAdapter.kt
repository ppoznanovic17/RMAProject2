package com.example.rma2.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rma2.R
import com.example.rma2.data.models.Class
import com.example.rma2.presentation.view.recycler.diff.ClassDiffCallback
import com.example.rma2.presentation.view.recycler.viewholder.ClassesListViewHolder

class ClassAdapter: ListAdapter<Class, ClassesListViewHolder>(ClassDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_class_list, parent, false)
        return ClassesListViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: ClassesListViewHolder, position: Int) {
        val cls = getItem(position)
        holder.bind(cls)
    }
}