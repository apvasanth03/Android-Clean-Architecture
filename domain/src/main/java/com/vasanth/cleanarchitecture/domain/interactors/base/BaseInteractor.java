package com.vasanth.cleanarchitecture.domain.interactors.base;

import com.vasanth.cleanarchitecture.domain.executor.Executor;
import com.vasanth.cleanarchitecture.domain.executor.PostExecutionThread;

/**
 * Base Interactor.
 * <p/>
 * 1. Responsibility.
 * 1.a. Interactor acts as base class for all our concrete interactors.
 *
 * @author Vasanth
 */
public abstract class BaseInteractor implements Interactor {

    // executor.
    protected Executor executor;

    // postExecutionThread.
    private PostExecutionThread postExecutionThread;

    /**
     * Constructor.
     *
     * @param executor            Executor.
     * @param postExecutionThread Post Execution Thread.
     */
    public BaseInteractor(final Executor executor, final PostExecutionThread postExecutionThread) {
        this.executor = executor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Used to execute interator in new thread using executor.
     */
    protected void executeInteractor() {
        executor.run(this);
    }

    /**
     * Used to post result in postExecutionThread.
     *
     * @param runnable Result Runnable to be posted in postExecutionThread.
     */
    protected void postResult(final Runnable runnable) {
        postExecutionThread.post(runnable);
    }
}
