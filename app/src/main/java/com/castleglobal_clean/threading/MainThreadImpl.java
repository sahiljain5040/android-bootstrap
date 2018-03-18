package com.castleglobal_clean.threading;

import android.os.Handler;
import android.os.Looper;

import com.demo.domain.executor.MainThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.from(mHandler);
    }
}
