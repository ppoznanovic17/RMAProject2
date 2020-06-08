package com.example.rma2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma2.data.models.Note
import com.example.rma2.data.models.NoteEntity
import com.example.rma2.data.repositories.NoteRepository
import com.example.rma2.presentation.contract.NoteContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NoteViewModel (private val noteRepository: NoteRepository) : ViewModel(), NoteContract.ViewModel{

    private val subs = CompositeDisposable()

    override val notes : MutableLiveData<List<Note>> = MutableLiveData()

    override fun insert(note: NoteEntity) {
        val sub = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Dodata beleska.")
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
    }

    override fun getAll(): LiveData<List<Note>>{
        val sub = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                   notes.value  = it
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
        return notes
    }

    override fun deleteById(id: Long) {
        val sub = noteRepository
            .deleteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Obrisana beleska.")
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
    }


    override fun getByIdAndUpdate(id: Long, title: String, content: String){
        val sub = noteRepository
            .getByIdAndUpdate(id, title, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Azirirana je beleska.")
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
    }

    override fun getByIdAndArchiveOrUnarchive(id: Long) {
        val sub = noteRepository
            .getByIdAndArchiveOrUnarchive(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Poruka je uspesno arhivirana/dearhivirana.")
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
    }

    override fun getNotesByTitleOrContent(string: String): LiveData<List<Note>> {
        val sub = noteRepository
            .getNotesByTitleOrContent(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notes.value  = it
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
        return notes
    }

    override fun getByArchived(archived: Int): LiveData<List<Note>> {
        val sub = noteRepository
            .getByArchived(archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notes.value  = it
                },
                {
                    Timber.e(it)
                }
            )
        subs.add(sub)
        return notes
    }

    override fun onCleared() {
        subs.clear()
        super.onCleared()
    }

}