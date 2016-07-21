package com.vasanth.cleanarchitecture.login.presenter.impl;

import com.vasanth.cleanarchitecture.UIThreadImpl;
import com.vasanth.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.vasanth.cleanarchitecture.domain.interactors.login.LoginInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.login.impl.LoginInteractorImpl;
import com.vasanth.cleanarchitecture.login.presenter.LoginPresenter;
import com.vasanth.cleanarchitecture.login.view.LoginView;

/**
 * Login Presenter Implementation.
 * <p>
 * 1. Responsibility.
 * 1.a. Implements LoginPresenter & provides functionality for it.
 *
 * @author Vasanth
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.LoginCallback {

    // LOGIN_STATE_NONE.
    private static final int LOGIN_STATE_NONE = 101;

    // LOGIN_STATE_LOGGING_IN.
    private static final int LOGIN_STATE_LOGGING_IN = 102;

    // LOGIN_STATE_LOGIN_SUCCESSFUL.
    private static final int LOGIN_STATE_LOGIN_SUCCESSFUL = 103;

    // LOGIN_STATE_LOGIN_FAILED.
    private static final int LOGIN_STATE_LOGIN_FAILED = 104;

    // loginView.
    private LoginView loginView;

    // loginInteractor.
    private LoginInteractor loginInteractor;

    // loginState.
    private int loginState;

    // errorCode.
    private int errorCode;

    /**
     * Constructor.
     */
    public LoginPresenterImpl() {
        loginInteractor = new LoginInteractorImpl(ThreadExecutor.getInstance(), UIThreadImpl.getInstance());
        loginState = LOGIN_STATE_NONE;
    }

    /**
     * On Attach View.
     * <p>
     * 1. Gets called on attach of view.
     *
     * @param view View.
     */
    @Override
    public void onAttachView(final LoginView view) {
        this.loginView = view;

        // Initialize Login View.
        initializeLoginView();
    }

    /**
     * On Detach View.
     * <p>
     * 1. Gets called on detach of view.
     */
    @Override
    public void onDetachView() {
        loginView = null;
    }

    /**
     * On Destroy View.
     * <p>
     * 1. Gets called on destroy of view.
     */
    @Override
    public void onDestroyView() {
        // If state is still loggingIn the cancel request.
        if (loginState == LOGIN_STATE_LOGGING_IN) {
            loginInteractor.cancelLogin();
            loginState = LOGIN_STATE_NONE;
        }
    }

    /**
     * Used to login user.
     *
     * @param userName UserName.
     * @param password Password.
     */
    @Override
    public void login(String userName, String password) {
        loginState = LOGIN_STATE_LOGGING_IN;
        loginInteractor.login(userName, password, this);
        notifyStateLoggingIn();
    }

    /**
     * Used to initialize login view.
     * <p>
     * 1. Initialize loginView depending on presenter state.
     */
    private void initializeLoginView() {
        switch (loginState) {
            // Logging In.
            case LOGIN_STATE_LOGGING_IN:
                notifyStateLoggingIn();
                break;

            // Login Successful.
            case LOGIN_STATE_LOGIN_SUCCESSFUL:
                notifyStateLoginSuccessful();
                break;

            // Login Failed.
            case LOGIN_STATE_LOGIN_FAILED:
                notifyStateLoginFailed(errorCode);
                break;
        }
    }

    /**
     * LoginInteractor.LoginCallback.
     */
    /**
     * On Login Success.
     */
    @Override
    public void onLoginSuccess() {
        loginState = LOGIN_STATE_LOGIN_SUCCESSFUL;
        notifyStateLoginSuccessful();
    }

    /**
     * On Login Failed.
     *
     * @param errorCode Error Code.
     */
    @Override
    public void onLoginFailed(int errorCode) {
        loginState = LOGIN_STATE_LOGIN_FAILED;
        this.errorCode = errorCode;
        notifyStateLoginFailed(errorCode);
    }

    /**
     * Methods used to send callback to loginView.
     */
    /**
     * Used to notify state LoggingIn.
     */
    private void notifyStateLoggingIn() {
        if (loginView != null) {
            loginView.resetUserNameFieldError();
            loginView.resetPasswordFieldError();
            loginView.showProgress();
        }
    }

    /**
     * Used to notify state LoginSuccessful.
     */
    private void notifyStateLoginSuccessful() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.navigateToTicketScreen();
            loginState = LOGIN_STATE_NONE;
        }
    }

    /**
     * Used to notify state LoginFailed.
     *
     * @param errorCode ErrorCode.
     */
    private void notifyStateLoginFailed(final int errorCode) {
        if (loginView != null) {
            loginView.hideProgress();
            if (errorCode == LoginInteractor.LOGIN_ERROR_CODE_INVALID_USERNAME) {
                // UserName invalid.
                loginView.setUserNameFieldInvalid();
            } else if (errorCode == LoginInteractor.LOGIN_ERROR_CODE_INVALID_PASSWORD) {
                // Password invalid.
                loginView.setPasswordFieldInvalid();
            }
            loginState = LOGIN_STATE_NONE;
        }
    }
}
