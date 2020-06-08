package com.example.rma2.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma2.data.models.ClassSearch
import com.example.rma2.data.models.Resource
import com.example.rma2.data.repositories.ClassRepository
import com.example.rma2.presentation.contract.ClassContract
import com.example.rma2.presentation.view.states.AddClassState
import com.example.rma2.presentation.view.states.ClassState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ClassViewModel(private val classRepository: ClassRepository): ViewModel(), ClassContract.ViewModel {


    override val classState: MutableLiveData<ClassState> = MutableLiveData()
    override val addDone: MutableLiveData<AddClassState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    private val publishSubject: PublishSubject<ClassSearch> = PublishSubject.create()



    init {
        val subscription = publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                classRepository
                    .getFilteredClasses(it.nastavnik, it.predmet, it.dan, it.grupe)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Greska pri pretrazi")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    classState.value = ClassState.Success(it)
                },
                {
                    classState.value = ClassState.Error("Greska pri prikupljanju podataka iz baze.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllClasses() {
        val subscription = classRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> classState.value = ClassState.Loading
                        is Resource.Success -> classState.value = ClassState.DataFetched
                        is Resource.Error -> classState.value = ClassState.Error("Greska pri preuzimanju. Gledate kesirane podatke.")
                    }
                },
                {
                    classState.value = ClassState.Error("Greska pri preuzimanju. Gledate kesirane podatke.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllClasses() {
        val subscription = classRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    classState.value = ClassState.Success(it)
                },
                {
                    classState.value = ClassState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getFilteredClasses(
        nastavnik: String,
        predmet: String,
        dan: String,
        grupa: String
    ) {
        val search = ClassSearch(predmet, nastavnik, grupa, dan)
        publishSubject.onNext(search)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}