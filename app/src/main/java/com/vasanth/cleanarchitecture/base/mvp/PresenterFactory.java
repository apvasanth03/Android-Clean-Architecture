package com.vasanth.cleanarchitecture.base.mvp;

/**
 * Presenter Factor.
 * <p>
 * 1. Responsibility.
 * 1.a. Used to create Presenter Object.
 *
 * @author Vasanth
 */
public interface PresenterFactory<T extends Presenter> {

    /**
     * Create Presenter.
     * <p>
     * 1. Used to create Presenter.
     *
     * @return Presenter.
     */
    T createPresenter();
}
