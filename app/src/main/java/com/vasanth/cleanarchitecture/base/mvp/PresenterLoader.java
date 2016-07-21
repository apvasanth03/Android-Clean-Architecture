package com.vasanth.cleanarchitecture.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

/**
 * Presenter Loader.
 * <p>
 * 1. Responsibility.
 * 1.a. Presenter Loader is used to create Presenter synchronously in UI Thread.
 * 1.b. We need to survive Presenter on Configuration changes (i.e On Configuration changes Activity will be destroyed and recreated but we need to
 * survive presenter on those cases).
 * 1.c. Android framework provides Loader which will be survived on orientation changes hence we have used Loader to create presenter & survive it on
 * Orientation changes.
 *
 * @author Vasanth
 */
public class PresenterLoader<T extends Presenter> extends Loader<T> {

    // presenterFactory.
    private PresenterFactory<T> presenterFactory;

    // savedInstanceState.
    private Bundle savedInstanceState;

    // presenter.
    private T presenter;

    /**
     * Constructor.
     *
     * @param context            Context.
     * @param presenterFactory   Presenter Factory.
     * @param savedInstanceState Saved Instance State.
     */
    public PresenterLoader(Context context, final PresenterFactory<T> presenterFactory,
                           final Bundle savedInstanceState) {
        super(context);
        this.presenterFactory = presenterFactory;
        this.savedInstanceState = savedInstanceState;
    }

    /**
     * On Start Loading.
     */
    @Override
    protected void onStartLoading() {
        // If we already have Presenter, then simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise force load data.
        forceLoad();
    }

    /**
     * On Force Load.
     */
    @Override
    protected void onForceLoad() {
        // Create Presenter using factory.
        presenter = presenterFactory.createPresenter();

        // Deliver the presenter.
        deliverResult(presenter);
    }

    /**
     * On Deliver Result.
     *
     * @param data Data.
     */
    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
    }


    /**
     * On Reset.
     */
    @Override
    protected void onReset() {
        if (presenter != null) {
            presenter = null;
        }
    }
}
