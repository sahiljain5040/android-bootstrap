package com.demo.domain.base.interactors

import com.demo.domain.base.executor.Executor
import com.demo.domain.base.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(open val threadExecutor: Executor,open val postExecutionThread: PostExecutionThread){

    private var disposables: CompositeDisposable
    private var lastCompositeDisposed = true

    init {
        disposables = CompositeDisposable()
    }

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    /**
     * This method contains the actual business logic of the interactor. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an interactor to make sure the operation is done on a background thread.
     * <p/>
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    fun execute(disposableObserver: DisposableObserver<T>, params: Params){
        this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(disposableObserver);

        disposables.add(disposableObserver)
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    fun dispose(){
        lastCompositeDisposed = true
        if (!disposables.isDisposed) {
            disposables.dispose()
            disposables = CompositeDisposable()
        }
    }

    /**
     * Get dispose status
     */
    fun isDisposed(): Boolean {
        return lastCompositeDisposed
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    protected fun addDisposable(disposable: Disposable) {
        lastCompositeDisposed = false
        disposables.add(disposable)
    }
}