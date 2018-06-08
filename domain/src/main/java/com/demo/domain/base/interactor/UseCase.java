package com.demo.domain.base.interactor;

import com.demo.domain.base.executor.PostExecutionThread;
import com.demo.domain.base.executor.impl.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private CompositeDisposable disposables;
    private boolean lastCompositeDisposed = true;

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Flowable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Flowable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param useCaseObserver {@link UseCaseSubscriber} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(UseCaseSubscriber<T> useCaseObserver, Params params) {

        final Flowable<T> useCaseObservable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

        useCaseObservable.subscribeWith(useCaseObserver);

        addDisposable(useCaseObserver);
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        lastCompositeDisposed = true;
        if (!disposables.isDisposed()) {
            disposables.dispose();
            disposables = new CompositeDisposable();
        }
    }

    /**
     * Get dispose status
     */
    public boolean isDisposed() {
        return lastCompositeDisposed;
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    protected void addDisposable(Disposable disposable) {
        lastCompositeDisposed = false;
        disposables.add(disposable);
    }
}
