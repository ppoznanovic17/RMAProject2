package com.example.rma2.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma2.data.models.Class
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_class_list.view.*

class ClassesListViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {



    fun bind(cls: Class){

        containerView.subjectClass.text = cls.predmet
        containerView.subjectTypeClass.text = cls.tip
        containerView.professorClass.text = cls.nastavnik
        containerView.classroomClass.text = cls.ucionica
        containerView.groupClass.text = cls.grupe
        containerView.dayClass.text = cls.dan
            containerView.timeClass.text = cls.termin
    }

}