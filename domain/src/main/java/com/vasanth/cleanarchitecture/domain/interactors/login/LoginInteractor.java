package com.vasanth.cleanarchitecture.domain.interactors.login;

/**
 * Login Interactor.
 * <p/>
 * 1. Responsibility.
 * 1.a. Interactor responsible to login user & send callback.
 *
 * @author Vasanth
 */
public interface LoginInteractor {

    // LOGIN_ERROR_CODE_INVALID_USERNAME.
    int LOGIN_ERROR_CODE_INVALID_USERNAME = 101;

    // LOGIN_ERROR_CODE_INVALID_PASSWORD.
    int LOGIN_ERROR_CODE_INVALID_PASSWORD = 102;

    /**
     * Login Callback.
     */
    interface LoginCallback {

        /**
         * Gets called if login is successful.
         */
        void onLoginSuccess();

        /**
         * Ges called if login is failed.
         *
         * @param errorCode Error Code.
         */
        void onLoginFailed(final int errorCode);

    }

    /**
     * Used to login user.
     *
     * @param userName      UserName.
     * @param password      Password.
     * @param loginCallback Login Callback.
     */
    void login(final String userName, final String password, final LoginCallback loginCallback);

    /**
     * Used to cancel login.
     */
    void cancelLogin();

}
