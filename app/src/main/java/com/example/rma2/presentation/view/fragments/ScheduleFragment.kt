package com.example.rma2.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.rma2.R
import com.example.rma2.presentation.contract.ClassContract
import com.example.rma2.presentation.view.recycler.adapter.ClassAdapter
import com.example.rma2.presentation.viewmodel.ClassViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma2.presentation.view.states.ClassState
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : Fragment(R.layout.fragment_schedule){


    private val classViewModel: ClassContract.ViewModel by sharedViewModel<ClassViewModel>()

    private lateinit var adapter: ClassAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }


    private fun initListeners() {
        professorSearch.doAfterTextChanged {
            val professor: String = it.toString()
            val subject: String = subjectSearch.text.toString()
            val group: String = groupSearch.text.toString()
            val day: String = daySearch.text.toString()

            Timber.e(professor + " " + subject + " " + day + " " + group)
            classViewModel.getFilteredClasses(professor, subject, day, group)
        }

        subjectSearch.doAfterTextChanged {
            val professor: String = professorSearch.text.toString()
            val subject: String = it.toString()
            val group: String = groupSearch.text.toString()
            val day: String = daySearch.text.toString()

            Timber.e(professor + " " + subject + " " + day + " " + group)
            classViewModel.getFilteredClasses(professor, subject, day, group)
        }

        groupSearch.doAfterTextChanged {
            val professor: String = professorSearch.text.toString()
            val subject: String = subjectSearch.text.toString()
            val group: String = it.toString()
            val day: String = daySearch.text.toString()

            Timber.e(professor + " " + subject + " " + day + " " + group)
            classViewModel.getFilteredClasses(professor, subject, day, group)
        }

        daySearch.doAfterTextChanged {
            val professor: String = professorSearch.text.toString()
            val subject: String = subjectSearch.text.toString()
            val group: String = groupSearch.text.toString()
            val day: String = it.toString()

            Timber.e(professor + " " + subject + " " + day + " " + group)
            classViewModel.getFilteredClasses(professor, subject, day, group)
        }
    }

    private fun initRecycler() {
        classList.layoutManager = LinearLayoutManager(context)
        adapter = ClassAdapter()
        classList.adapter = adapter
    }



    private fun initObservers() {
        classViewModel.classState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        classViewModel.getAllClasses()

        classViewModel.fetchAllClasses()
    }

    private fun renderState(state: ClassState) {
        when (state) {
            is ClassState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.classes)
            }
            is ClassState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ClassState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Podaci su prikupljeni sa servera", Toast.LENGTH_LONG).show()
            }
            is ClassState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        professorSearch.isVisible = !loading
        groupSearch.isVisible = !loading
        daySearch.isVisible = !loading
        subjectSearch.isVisible = !loading
        line.isVisible = !loading
        loadingPb.isVisible = loading
    }

}