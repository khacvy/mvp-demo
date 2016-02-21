package project.com.demo.mvp.login.presenter;

import project.com.demo.mvp.common.Utils;
import project.com.demo.mvp.login.message.ErrorMessage;
import project.com.demo.mvp.login.model.CheckLoginTask;
import project.com.demo.mvp.login.view.ILoginView;

/**
 * Created by Khắc Vỹ on 2/21/2016.
 */
public class LoginPresenter implements ILoginPresenter, CheckLoginTask.CheckLoginListener {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLoginButtonClick(String email, String password) {
        if (validate(email, password)) {
            loginView.showLoading();
            CheckLoginTask checkLoginTask = new CheckLoginTask(this);
            checkLoginTask.checkLogin(email, password);
        }
    }

    private boolean validate(String email, String password) {
        boolean valid = true;

        if (email.isEmpty()) {
            loginView.showEmailError(ErrorMessage.MISSING_EMAIL_ERROR);
            valid = false;
        } else if (!Utils.checkValidEmail(email)) {
            loginView.showEmailError(ErrorMessage.INVALID_EMAIL_FORMAT_ERROR);
            valid = false;
        }
        if(!valid){
            return valid;
        }

        if (password.isEmpty()) {
            loginView.showPasswordError(ErrorMessage.MISSING_PASSWORD_ERROR);
            valid = false;
        } else if (password.length() < 6) {
            loginView.showPasswordError(ErrorMessage.INVALID_PASSWORD_LENGTH_ERROR);
            valid = false;
        }

        return valid;
    }

    @Override
    public void onLoginSuccess(String email) {
        loginView.hideLoading();
        loginView.gotoMainScreen(email);
    }

    @Override
    public void onLoginFailed() {
        loginView.hideLoading();
        loginView.showLoginFail();
    }
}
