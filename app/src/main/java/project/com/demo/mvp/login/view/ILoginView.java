package project.com.demo.mvp.login.view;

/**
 * Created by Khắc Vỹ on 2/21/2016.
 */
public interface ILoginView {
    /**
     * Show loading indicator when query data
     */
    void showLoading();

    /**
     * Hide loading indicator
     */
    void hideLoading();

    /**
     * Show error message when input invalid email
     *
     * @param messageId
     */
    void showEmailError(int messageId);

    /**
     * Show error message when input invalid password
     *
     * @param messageId
     */
    void showPasswordError(int messageId);

    /**
     * show error message when login failed
     */
    void showLoginFail();

    /**
     * Login success, go to main screen
     *
     * @param email
     */
    void gotoMainScreen(String email);
}
