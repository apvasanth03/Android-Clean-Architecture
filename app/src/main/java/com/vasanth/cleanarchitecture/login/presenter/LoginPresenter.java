package com.vasanth.cleanarchitecture.login.presenter;

import com.vasanth.cleanarchitecture.base.mvp.Presenter;
import com.vasanth.cleanarchitecture.login.view.LoginView;

/**
 * Login Presenter.
 * <p>
 * 1. Responsibility.
 * 1.a. Login Presenter - Defines method which acts as a bridge between loginUI & loginData.
 *
 * @author Vasanth
 */
public interface LoginPresenter extends Presenter<LoginView> {

    /**
     * Used to login user.
     *
     * @param userName UserName.
     * @param password Password.
     */
    void login(final String userName, final String password);

}
