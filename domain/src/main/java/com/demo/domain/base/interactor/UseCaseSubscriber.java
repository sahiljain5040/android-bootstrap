package com.demo.domain.base.interactor;

import io.reactivex.subscribers.DisposableSubscriber;

public class UseCaseSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable exception) {

    }
}

