package com.demo.domain.interactors

import com.demo.domain.executor.Executor
import com.demo.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(private val mThreadExecutor: Executor, private val mPostExecutionThread
                                        : PostExecutionThread) {

    private var disposables: CompositeDisposable
    /**
     * Get dispose status
     */
    var isDisposed = true
        private set

    init {
        this.disposables = CompositeDisposable()
    }

    /**
     * This method contains the actual business logic of the interactor. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an interactor to make sure the operation is done on a background thread.
     *
     *
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(disposableObserver: DisposableObserver<T>, params: Params) {

        this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(disposableObserver)

        addDisposable(disposableObserver)
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        isDisposed = true
        if (!disposables.isDisposed) {
            disposables.dispose()
            disposables = CompositeDisposable()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        isDisposed = false
        disposables.add(disposable)
    }

}