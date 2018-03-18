package com.demo.domain.interactors;

import com.demo.domain.executor.Executor;
import com.demo.domain.executor.PostExecutionThread;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmilicic on 8/4/15.
 * <p/>
 * This abstract class implements some common methods for all interactors. Cancelling an interactor, check if its running
 * and finishing an interactor has mostly the same code throughout so that is why this class was created. Field methods
 * are declared volatile as we might use these methods from different threads (mainly from UI).
 * <p/>
 * For example, when an activity is getting destroyed then we should probably cancel an interactor
 * but the request will come from the UI thread unless the request was specifically assigned to a background thread.
 */
public abstract class UseCase<T, Params>{

    protected Executor mThreadExecutor;
    protected PostExecutionThread mPostExecutionThread;
    private CompositeDisposable disposables;
    private boolean lastCompositeDisposed = true;

    public UseCase(Executor threadExecutor, PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * This method contains the actual business logic of the interactor. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an interactor to make sure the operation is done on a background thread.
     * <p/>
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    public abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(UseCaseObserver<T> useCaseObserver, Params params) {

        this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(useCaseObserver);

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
