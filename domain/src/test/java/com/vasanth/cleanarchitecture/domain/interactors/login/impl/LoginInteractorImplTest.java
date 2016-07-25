package com.vasanth.cleanarchitecture.domain.interactors.login.impl;

import com.vasanth.cleanarchitecture.domain.executor.TestExecutor;
import com.vasanth.cleanarchitecture.domain.executor.TestPostExecutionThread;
import com.vasanth.cleanarchitecture.domain.interactors.login.LoginInteractor;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Login Interactor Implementation Test.
 * <p/>
 * 1. Responsibility.
 * 1.a. Class used to unit test LoginInteractorImpl
 *
 * @author Vasanth
 * @see LoginInteractorImpl
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginInteractorImplTest {

    // loginInteractorImpl.
    private LoginInteractorImpl loginInteractorImpl;

    // userName.
    private String userName;

    // password.
    private String password;

    // isLoginSuccess.
    private boolean isLoginSuccess;

    // errorCode.
    private int errorCode;

    /**
     * Constructor.
     */
    public LoginInteractorImplTest() {

    }

    /**
     * Set Up.
     * <p/>
     * 1. Create instance of LoginInteractorImpl.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        loginInteractorImpl = new LoginInteractorImpl(TestExecutor.getInstance(), TestPostExecutionThread.getInstance());
    }

    /**
     * Used to test - Login Failed due to invalid username.
     *
     * @throws Exception
     */
    @Test
    public void test01LoginFailedInvalidUsername() throws Exception {
        userName = "";
        password = "";
        isLoginSuccess = false;
        errorCode = -1;
        loginInteractorImpl.login(userName, password, new LoginInteractor.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                isLoginSuccess = true;
            }

            @Override
            public void onLoginFailed(int errorCode) {
                isLoginSuccess = false;
                LoginInteractorImplTest.this.errorCode = errorCode;
            }
        });

        // Validations.
        assertThat("Login should be failed", isLoginSuccess, is(false));
        assertThat("Error should be Invalid UserName", errorCode, is(LoginInteractor.LOGIN_ERROR_CODE_INVALID_USERNAME));
    }

    /**
     * Used to test - Login Failed due to invalid password.
     *
     * @throws Exception
     */
    @Test
    public void test02LoginFailedInvalidPassword() throws Exception {
        userName = "user";
        password = "";
        isLoginSuccess = false;
        errorCode = -1;
        loginInteractorImpl.login(userName, password, new LoginInteractor.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                isLoginSuccess = true;
            }

            @Override
            public void onLoginFailed(int errorCode) {
                isLoginSuccess = false;
                LoginInteractorImplTest.this.errorCode = errorCode;
            }
        });

        // Validations.
        assertThat("Login should be failed", isLoginSuccess, is(false));
        assertThat("Error should be Invalid Password", errorCode, is(LoginInteractor.LOGIN_ERROR_CODE_INVALID_PASSWORD));
    }

    /**
     * Used to test Login success case.
     *
     * @throws Exception
     */
    @Test
    public void test03LoginSuccess() throws Exception {
        userName = "user";
        password = "pass";
        isLoginSuccess = false;
        errorCode = -1;
        loginInteractorImpl.login(userName, password, new LoginInteractor.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                isLoginSuccess = true;
            }

            @Override
            public void onLoginFailed(int errorCode) {
                isLoginSuccess = false;
                LoginInteractorImplTest.this.errorCode = errorCode;
            }
        });

        // Validations.
        assertThat("Login should be successful", isLoginSuccess, is(true));
    }
}
