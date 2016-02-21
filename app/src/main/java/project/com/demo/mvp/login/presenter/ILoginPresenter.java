package project.com.demo.mvp.login.presenter;

/**
 * Created by Khắc Vỹ on 2/21/2016.
 */
public interface ILoginPresenter {
    /**
     * check login
     *
     * @param email
     * @param password
     */
    void onLoginButtonClick(String email, String password);
}
