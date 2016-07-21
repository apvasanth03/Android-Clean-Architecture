package com.vasanth.cleanarchitecture.domain.executor;

import com.vasanth.cleanarchitecture.domain.interactors.base.Interactor;

/**
 * Executor.
 * <p/>
 * 1. Responsibility.
 * 1.a. Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the Interactor out of the UI thread.
 * 1.b. Use this to execute an Interactor.
 *
 * @author Vasanth
 */
public interface Executor {

    /**
     * Run.
     * <p/>
     * 1. The class which overrides this method should make sure that it executes interactor in new thread.
     *
     * @param interactor Interactor.
     */
    void run(final Interactor interactor);

}
