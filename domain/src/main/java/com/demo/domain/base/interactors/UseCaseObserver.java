package com.demo.domain.base.interactors;

/**
 * Created by sahil on 3/17/18.
 */

import io.reactivex.observers.DisposableObserver;

public class UseCaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
