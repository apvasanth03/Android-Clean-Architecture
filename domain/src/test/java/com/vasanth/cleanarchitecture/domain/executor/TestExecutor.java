package com.vasanth.cleanarchitecture.domain.executor;

import com.vasanth.cleanarchitecture.domain.interactors.base.Interactor;

/**
 * Test Executor.
 * <p/>
 * 1. Responsibility.
 * 1.a. Test Executor is a dummy test class implements Executor & executes interactor.
 *
 * @author Vasanth
 */
public class TestExecutor implements Executor {

    // instance.
    private static TestExecutor instance;

    /**
     * Constructor.
     */
    private TestExecutor() {

    }

    /**
     * Used to get single ton instance of TestExecutor.
     *
     * @return Single ton instance of TestExecutor.
     */
    public static TestExecutor getInstance() {
        if (instance == null) {
            instance = new TestExecutor();
        }
        return instance;
    }

    /**
     * Run.
     * <p/>
     * 1. In actual implementation we will run the interacotr in new thread but for testing purpose we will run interactor in current thread itself.
     *
     * @param interactor Interactor.
     */
    @Override
    public void run(Interactor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor to execute can't be null");
        }
        interactor.run();
    }
}
