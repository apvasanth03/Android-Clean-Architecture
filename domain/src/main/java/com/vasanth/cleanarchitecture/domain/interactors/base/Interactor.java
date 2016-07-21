package com.vasanth.cleanarchitecture.domain.interactors.base;

/**
 * Interactor.
 * <p/>
 * 1. Responsibility.
 * 1.a. Common interface to every Interactor declared in the application. This interface represents a
 * execution unit for different use cases.
 * 1.b. By convention every interactor implementation will return the result using a Callback. That
 * callback should be executed over the UI thread.
 *
 * @author Vasanth
 */
public interface Interactor {

    /**
     * Run
     * <p/>
     * 1. This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void run();
}
