package com.vasanth.cleanarchitecture.domain.executor;

/**
 * Post Execution Thread.
 * <p/>
 * 1. Responsibility.
 * 1.a. Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 *
 * @author Vasanth
 */
public interface PostExecutionThread {

    /**
     * Make runnable operation run in the post execution thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);

}
