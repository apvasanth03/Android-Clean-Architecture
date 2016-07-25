package com.vasanth.cleanarchitecture.domain.executor.impl;

import com.vasanth.cleanarchitecture.domain.executor.Executor;
import com.vasanth.cleanarchitecture.domain.interactors.base.Interactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread Executor.
 * <p>
 * 1. Responsibility.
 * 1.a. Thread Executor implements Executor and makes sure that each interactor gets executed in new thread.
 *
 * @author Vasanth
 * @see Executor
 */
public class ThreadExecutor implements Executor {

    // instance.
    private static ThreadExecutor instance;

    // CORE_POOL_SIZE.
    private static final int CORE_POOL_SIZE = 3;

    // MAX_POOL_SIZE.
    private static final int MAX_POOL_SIZE = 5;

    // KEEP_ALIVE_TIME.
    private static final int KEEP_ALIVE_TIME = 120;

    // TIME_UNIT.
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    // WORK_QUEUE.
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    // threadPoolExecutor.
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * Constructor.
     */
    private ThreadExecutor() {
        threadPoolExecutor =
                new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                        TIME_UNIT, WORK_QUEUE);
    }

    /**
     * Used to get single ton instance of ThreadExecutor.
     *
     * @return single ton instance of ThreadExecutor.
     */
    public static ThreadExecutor getInstance() {
        if (instance == null) {
            instance = new ThreadExecutor();
        }
        return instance;
    }

    /**
     * Run
     * <p>
     * 1. Executes interator in new thread.
     *
     * @param interactor Interactor.
     */
    @Override
    public void run(final Interactor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor to execute can't be null");
        }
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
            }
        });
    }

}
