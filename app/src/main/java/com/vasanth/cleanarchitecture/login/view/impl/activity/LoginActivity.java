package com.vasanth.cleanarchitecture.login.view.impl.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.vasanth.cleanarchitecture.R;
import com.vasanth.cleanarchitecture.base.mvp.PresenterFactory;
import com.vasanth.cleanarchitecture.base.mvp.PresenterLoader;
import com.vasanth.cleanarchitecture.login.presenter.LoginPresenter;
import com.vasanth.cleanarchitecture.login.presenter.impl.LoginPresenterImpl;
import com.vasanth.cleanarchitecture.login.view.LoginView;
import com.vasanth.cleanarchitecture.ticket.view.impl.activity.TicketActivity;

/**
 * Login Activity.
 * <p/>
 * 1. Responsibility.
 * 1.a. Provides UI for login screen.
 *
 * @author Vasanth
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener, LoaderManager.LoaderCallbacks<LoginPresenter> {

    // LOADER_ID_LOGIN_PRESENTER.
    private static final int LOADER_ID_LOGIN_PRESENTER = 101;

    // STATE_KEY_USERNAME.
    private static final String STATE_KEY_USERNAME = "state_key_username";

    // STATE_KEY_PASSWORD.
    private static final String STATE_KEY_PASSWORD = "state_key_password";

    // STATE_KEY_REMEMBER_ME.
    private static final String STATE_KEY_REMEMBER_ME = "state_key_rememberMe";

    // userNameEditText.
    private EditText userNameEditText;

    // passwordEditText.
    private EditText passwordEditText;

    // rememberMeCheckBox.
    private CheckBox rememberMeCheckBox;

    // loginButton.
    private Button loginButton;

    // loginProgressDialog.
    private ProgressDialog loginProgressDialog;

    // loginPresenter.
    private LoginPresenter loginPresenter;

    /**
     * On Create.
     *
     * @param savedInstanceState Saved Instance State.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views.
        initializeViews();

        // Create presenter using loader.
        getSupportLoaderManager().initLoader(LOADER_ID_LOGIN_PRESENTER, null, this);

        // Restore login state.
        restoreLoginViewState(savedInstanceState);
    }

    /**
     * On Start.
     */
    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.onAttachView(this);
    }

    /**
     * On Stop.
     */
    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.onDetachView();
    }

    /**
     * On Save Instance State.
     *
     * @param outState Out State.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveLoginViewState(outState);
    }

    /**
     * Used to save login view state.
     *
     * @param outState Out State.
     */
    private void saveLoginViewState(final Bundle outState) {
        outState.putString(STATE_KEY_USERNAME, getUserNameFieldValue());
        outState.putString(STATE_KEY_PASSWORD, getPasswordFieldValue());
        outState.putBoolean(STATE_KEY_REMEMBER_ME, getRememberMeFieldValue());
    }

    /**
     * Used to restore login view state.
     *
     * @param savedInstanceState Saved Instance State.
     */
    private void restoreLoginViewState(final Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String userName = savedInstanceState.getString(STATE_KEY_USERNAME, "");
            String password = savedInstanceState.getString(STATE_KEY_PASSWORD, "");
            boolean rememberMe = savedInstanceState.getBoolean(STATE_KEY_REMEMBER_ME, false);

            setUserNameFieldValue(userName);
            setPasswordFieldValue(password);
            setRememberMeFieldValue(rememberMe);
        }
    }

    /**
     * Used to initialize views.
     */
    private void initializeViews() {
        userNameEditText = (EditText) findViewById(R.id.activityLogin_view_userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.activityLogin_view_passwordEditText);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.activityLogin_view_rememberMeCheckBox);
        loginButton = (Button) findViewById(R.id.activityLogin_view_loginButton);
        loginButton.setOnClickListener(this);
    }

    /**
     * LoaderManager.LoaderCallbacks<LoginPresenter>
     */
    /**
     * On Create Loader.
     *
     * @param id   Id.
     * @param args Args.
     * @return Loader.
     */
    @Override
    public Loader<LoginPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new LoginPresenterFactory(), args);
    }

    /**
     * On Load Finished.
     *
     * @param loader Loader.
     * @param data   Data.
     */
    @Override
    public void onLoadFinished(Loader<LoginPresenter> loader, LoginPresenter data) {
        this.loginPresenter = data;
    }

    /**
     * On Loader Reset.
     *
     * @param loader Loader.
     */
    @Override
    public void onLoaderReset(Loader<LoginPresenter> loader) {
        this.loginPresenter.onDestroyView();
        this.loginPresenter = null;
    }

    /**
     * Login Presenter Factory.
     */
    private static class LoginPresenterFactory implements PresenterFactory<LoginPresenter> {
        /**
         * Create Presenter.
         *
         * @return Login Presenter.
         */
        @Override
        public LoginPresenter createPresenter() {
            return new LoginPresenterImpl();
        }
    }

    /**
     * LoginView, View.OnClickListener.
     */
    /**
     * On Click.
     *
     * @param v View.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activityLogin_view_loginButton) {
            loginUser();
        }
    }

    /**
     * Gets called on click of login button, used to login user.
     */
    private void loginUser() {
        loginPresenter.login(getUserNameFieldValue(), getPasswordFieldValue());
    }

    /**
     * LoginView.
     */
    /**
     * Used to show progress.
     */
    @Override
    public void showProgress() {
        if (loginProgressDialog == null || !loginProgressDialog.isShowing()) {
            loginProgressDialog = ProgressDialog.show(this, getString(R.string.login), getString(R.string.please_wait), true, false);
        }
    }

    /**
     * Used to hide progress.
     */
    @Override
    public void hideProgress() {
        if (loginProgressDialog != null && loginProgressDialog.isShowing()) {
            loginProgressDialog.dismiss();
        }
    }

    /**
     * Used to set userName field value.
     *
     * @param userName UserName.
     */
    @Override
    public void setUserNameFieldValue(String userName) {
        userNameEditText.setText(userName);
    }

    /**
     * Used to get userName field value.
     *
     * @return UserName.
     */
    @Override
    public String getUserNameFieldValue() {
        return userNameEditText.getText().toString();
    }

    /**
     * Used to set error on userName field.
     */
    @Override
    public void setUserNameFieldInvalid() {
        userNameEditText.setError(getString(R.string.username_invalid));
    }

    /**
     * Used to reset error in userName field.
     */
    @Override
    public void resetUserNameFieldError() {
        userNameEditText.setError(null);
    }

    /**
     * Used to set password field value.
     *
     * @param password Password.
     */
    @Override
    public void setPasswordFieldValue(String password) {
        passwordEditText.setText(password);
    }

    /**
     * Used to get password field value.
     *
     * @return UserName.
     */
    @Override
    public String getPasswordFieldValue() {
        return passwordEditText.getText().toString();
    }

    /**
     * Used to set error on password field.
     */
    @Override
    public void setPasswordFieldInvalid() {
        passwordEditText.setError(getString(R.string.password_invalid));
    }

    /**
     * Used to rest error in password field.
     */
    @Override
    public void resetPasswordFieldError() {
        passwordEditText.setError(null);
    }

    /**
     * Used to set remember me field value.
     *
     * @param rememberMe Remember Me.
     */
    @Override
    public void setRememberMeFieldValue(boolean rememberMe) {
        rememberMeCheckBox.setChecked(rememberMe);
    }

    /**
     * Used to get remember me field value.
     *
     * @return Remember Me.
     */
    @Override
    public boolean getRememberMeFieldValue() {
        return rememberMeCheckBox.isChecked();
    }

    /**
     * Used to navigate to ticket screen.
     */
    public void navigateToTicketScreen() {
        Intent ticketIntent = new Intent(this, TicketActivity.class);
        ticketIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ticketIntent);
        finish();
    }
}
