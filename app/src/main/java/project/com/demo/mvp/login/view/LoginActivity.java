package project.com.demo.mvp.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.com.demo.mvp.R;
import project.com.demo.mvp.login.presenter.ILoginPresenter;
import project.com.demo.mvp.login.presenter.LoginPresenter;
import project.com.demo.mvp.main.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    /**
     * UI Control
     */
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    /**
     * Presenter
     */
    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        initObject();
        initEvent();
    }

    /**
     * Register UI Event
     */
    private void initEvent() {
        btnLogin.setOnClickListener(this);
    }

    /**
     * Load UI Control
     */
    private void initObject() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Authenticating ...");
    }

    /**
     * Show loading indicator when query data
     */
    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showEmailError(int messageId) {
        txtEmail.setError(getString(messageId));
        txtEmail.requestFocus();
    }

    @Override
    public void showPasswordError(int messageId) {
        txtPassword.setError(getString(messageId));
        txtPassword.requestFocus();
    }

    @Override
    public void showLoginFail() {
        Toast.makeText(getBaseContext(), R.string.error_login_failed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotoMainScreen(String email) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        loginPresenter.onLoginButtonClick(email, password);
    }

}
