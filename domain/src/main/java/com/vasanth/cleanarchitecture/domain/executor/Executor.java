package com.vasanth.cleanarchitecture.domain.executor;

import com.vasanth.cleanarchitecture.domain.interactors.base.Interactor;

/**
 * Executor.
 * <p>
 * 1. Responsibility.
 * 1.a. Executor is responsible to execute an Interactor in new thread (Out of the UI thread).
 *
 * @author Vasanth
 */
public interface Executor {

    /**
     * Run.
     * <p>
     * 1. The class which overrides this method should make sure that it executes interactor in new thread.
     *
     * @param interactor Interactor.
     */
    void run(final Interactor interactor);

}
