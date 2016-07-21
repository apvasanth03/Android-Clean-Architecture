package com.vasanth.cleanarchitecture.base.mvp;

/**
 * Presenter.
 * <p>
 * 1. Responsibility.
 * 1.a. Base for all our Presenter's.
 * 1.b. Holds common methods for our presenter.
 *
 * @author Vasanth
 */
public interface Presenter<V extends View> {

    /**
     * On Attach View.
     * <p>
     * 1. Gets called on attach of view.
     *
     * @param view View.
     */
    void onAttachView(V view);

    /**
     * On Detach View.
     * <p>
     * 1. Gets called on detach of view.
     */
    void onDetachView();

    /**
     * On Destroy View.
     * <p>
     * 1. Gets called on destroy of view.
     */
    void onDestroyView();

}
