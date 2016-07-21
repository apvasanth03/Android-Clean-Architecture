package com.vasanth.cleanarchitecture.login.view;

import com.vasanth.cleanarchitecture.base.mvp.View;

/**
 * Login View.
 * <p>
 * 1. Responsibility.
 * 1.a. LoginView - Defines method which should be implemented by class which provides Ui for login screen.
 *
 * @author Vasanth
 */
public interface LoginView extends View {

    /**
     * PROGRESS VIEW.
     */
    /**
     * Used to show progress.
     */
    void showProgress();

    /**
     * Used to hide progress.
     */
    void hideProgress();

    /**
     * USERNAME VIEW.
     */
    /**
     * Used to set userName field value.
     *
     * @param userName UserName.
     */
    void setUserNameFieldValue(final String userName);

    /**
     * Used to get userName field value.
     *
     * @return UserName.
     */
    String getUserNameFieldValue();

    /**
     * Used to set userName field invalid.
     */
    void setUserNameFieldInvalid();

    /**
     * Used to reset error in userName field.
     */
    void resetUserNameFieldError();

    /**
     * PASSWORD VIEW.
     */
    /**
     * Used to set password field value.
     *
     * @param password Password.
     */
    void setPasswordFieldValue(final String password);

    /**
     * Used to get password field value.
     *
     * @return UserName.
     */
    String getPasswordFieldValue();

    /**
     * Used to set password field inValid.
     */
    void setPasswordFieldInvalid();

    /**
     * Used to rest error in password field.
     */
    void resetPasswordFieldError();

    /**
     * REMEMBER_ME VIEW.
     */
    /**
     * Used to set remember me field value.
     *
     * @param rememberMe Remember Me.
     */
    void setRememberMeFieldValue(final boolean rememberMe);

    /**
     * Used to get remember me field value.
     *
     * @return Remember Me.
     */
    boolean getRememberMeFieldValue();

    /**
     * NAVIGATION METHODS.
     */
    /**
     * Used to navigate to ticket screen.
     */
    void navigateToTicketScreen();

}
