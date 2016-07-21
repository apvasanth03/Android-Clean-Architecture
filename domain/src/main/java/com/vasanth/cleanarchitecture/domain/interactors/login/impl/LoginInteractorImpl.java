package com.vasanth.cleanarchitecture.domain.interactors.login.impl;

import com.vasanth.cleanarchitecture.domain.executor.Executor;
import com.vasanth.cleanarchitecture.domain.executor.PostExecutionThread;
import com.vasanth.cleanarchitecture.domain.interactors.base.BaseInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.login.LoginInteractor;

/**
 * Login Interactor Implementation.
 * <p/>
 * 1. Responsibility.
 * 1.a. Implements LoginInteractor & provides functionality to login user.
 *
 * @author Vasanth
 */
public class LoginInteractorImpl extends BaseInteractor implements LoginInteractor {

    // userName.
    private String userName;

    // password.
    private String password;

    // loginCallback.
    private LoginCallback loginCallback;

    /**
     * Constructor.
     *
     * @param executor            Executor.
     * @param postExecutionThread Post Execution Thread.
     */
    public LoginInteractorImpl(Executor executor, PostExecutionThread postExecutionThread) {
        super(executor, postExecutionThread);
    }

    /**
     * Used to login user.
     *
     * @param userName      UserName.
     * @param password      Password.
     * @param loginCallback Login Callback.
     */
    public void login(final String userName, final String password, final LoginCallback loginCallback) {
        this.userName = userName;
        this.password = password;
        this.loginCallback = loginCallback;

        // Execute interactor in new thread.
        executeInteractor();
    }

    /**
     * Used to cancel login.
     */
    @Override
    public void cancelLogin() {
        loginCallback = null;
    }

    /**
     * Run.
     * <p/>
     * 1. This method runs in new thread.
     * 2. Hence do our logic here & send callback using postExecutionThread.
     */
    @Override
    public void run() {

        // let's simulate some network/database lag
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (userName == null || userName.isEmpty()) {
            // UserName Invalid.
            notifyUserNameInvalid();
        } else if (password == null || password.isEmpty()) {
            // Password Invalid.
            notifyPasswordInvalid();
        } else {
            // Login Success.
            notifyLoginSuccess();
        }
    }

    /**
     * Use to notify that userName is invalid.
     */
    private void notifyUserNameInvalid() {
        if (loginCallback != null) {
            postResult(new Runnable() {
                @Override
                public void run() {
                    loginCallback.onLoginFailed(LOGIN_ERROR_CODE_INVALID_USERNAME);
                }
            });
        }
    }

    /**
     * Use to notify that password is invalid.
     */
    private void notifyPasswordInvalid() {
        if (loginCallback != null) {
            postResult(new Runnable() {
                @Override
                public void run() {
                    loginCallback.onLoginFailed(LOGIN_ERROR_CODE_INVALID_PASSWORD);
                }
            });
        }
    }

    /**
     * Use to notify that login is successful.
     */
    private void notifyLoginSuccess() {
        if (loginCallback != null) {
            postResult(new Runnable() {
                @Override
                public void run() {
                    loginCallback.onLoginSuccess();
                }
            });
        }
    }
}
