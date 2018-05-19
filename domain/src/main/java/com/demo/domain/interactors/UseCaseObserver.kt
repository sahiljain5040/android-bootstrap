package com.demo.domain.interactors

import io.reactivex.observers.DisposableObserver

class UseCaseObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(error: Throwable) {
    }

}