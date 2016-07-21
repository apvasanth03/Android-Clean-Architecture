package com.vasanth.cleanarchitecture;

import android.os.Handler;
import android.os.Looper;

import com.vasanth.cleanarchitecture.domain.executor.PostExecutionThread;

/**
 * UI Thread Impl.
 * <p/>
 * 1. Responsibility.
 * 1.a. The class implements PostExecutionThread & is responsible to runs the given runnable in android ui thread.
 *
 * @author Vasanth
 */
public class UIThreadImpl implements PostExecutionThread {

    // instance.
    private static UIThreadImpl instance;

    // handler.
    private Handler handler;


    /**
     * Constrictor.
     */
    private UIThreadImpl() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Used to get single ton instance of UIThreadImpl.
     *
     * @return single ton instance of UIThreadImpl.
     */
    public static UIThreadImpl getInstance() {
        if (instance == null) {
            instance = new UIThreadImpl();
        }
        return instance;
    }

    /**
     * Will run the given runnable in given UI Thread.
     *
     * @param runnable The runnable to run.
     */
    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
