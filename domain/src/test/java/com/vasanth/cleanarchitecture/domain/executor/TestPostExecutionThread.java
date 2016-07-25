package com.vasanth.cleanarchitecture.domain.executor;

/**
 * Test Post Execution Thread.
 * <p/>
 * 1. Responsibility.
 * 1.a. TestPostExecutionThread - Dummy test class.
 *
 * @author Vasanth
 */
public class TestPostExecutionThread implements PostExecutionThread {

    // instance.
    private static TestPostExecutionThread instance;

    /**
     * Constructor.
     */
    private TestPostExecutionThread() {

    }

    /**
     * Used to get single ton instance of TestPostExecutionThread.
     *
     * @return Single ton instance of TestPostExecutionThread.
     */
    public static TestPostExecutionThread getInstance() {
        if (instance == null) {
            instance = new TestPostExecutionThread();
        }
        return instance;
    }

    /**
     * Post.
     * <p/>
     * 1. In actual implementation we will make the runnable to run in UI thread but for testing purpose we will just run the runnable in current thread.
     *
     * @param runnable The runnable to run.
     */
    @Override
    public void post(Runnable runnable) {
        runnable.run();
    }
}
