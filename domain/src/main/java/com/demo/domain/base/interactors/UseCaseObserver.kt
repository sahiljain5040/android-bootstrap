package com.demo.domain.base.interactors

import io.reactivex.observers.DisposableObserver

abstract class UseCaseObserver<T>: DisposableObserver<T>() {
    
    override fun onNext(t: T) {
        
    }

    override fun onError(e: Throwable) {

    }
    
    override fun onComplete() {

    }
    
}