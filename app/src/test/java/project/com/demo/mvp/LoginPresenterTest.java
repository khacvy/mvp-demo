package project.com.demo.mvp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import project.com.demo.mvp.login.message.ErrorMessage;
import project.com.demo.mvp.login.model.CheckLoginTask;
import project.com.demo.mvp.login.presenter.ILoginPresenter;
import project.com.demo.mvp.login.presenter.LoginPresenter;
import project.com.demo.mvp.login.view.ILoginView;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Khắc Vỹ on 2/21/2016.
 * Unit test for implementation of {@link project.com.demo.mvp.login.presenter.LoginPresenter}
 */
public class LoginPresenterTest {

    @Mock
    private ILoginView loginView;
    //@Mock
    private CheckLoginTask checkLoginTask;

    @Captor
    private ArgumentCaptor<CheckLoginTask.CheckLoginListener> checkLoginListenerArgumentCaptor;

    private ILoginPresenter loginPresenter;

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);

        loginPresenter = new LoginPresenter(loginView);
    }

    @Test
    public void inputEmptyEmail_showEmailError() {
        String email = "";
        String password = "xyz";
        loginPresenter.onLoginButtonClick(email, password);
        verify(loginView).showEmailError(ErrorMessage.MISSING_EMAIL_ERROR);
    }

    @Test
    public void inputInvalidEmailFormat_showEmailError() {
        String email = "invalid_email";
        String password = "xyz";
        loginPresenter.onLoginButtonClick(email, password);
        verify(loginView).showEmailError(ErrorMessage.INVALID_EMAIL_FORMAT_ERROR);
    }

    @Test
    public void inputEmptyPassword_showPasswordError() {
        String email = "admin@yahoo.com";
        String password = "";
        loginPresenter.onLoginButtonClick(email, password);
        verify(loginView).showPasswordError(ErrorMessage.MISSING_PASSWORD_ERROR);
    }

    @Test
    public void inputInvalidPasswordLength_showPasswordError() {
        String email = "admin@yahoo.com";
        String password = "123";
        loginPresenter.onLoginButtonClick(email, password);
        verify(loginView).showPasswordError(ErrorMessage.INVALID_PASSWORD_LENGTH_ERROR);
    }

    @Test
    public void inputInvalidEmailOrPassword_showLoginFailed() {
        String email = "email@yahoo.com";
        String password = "invalid_password";

        checkLoginTask = new CheckLoginTask(checkLoginListenerArgumentCaptor.capture());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(checkLoginListenerArgumentCaptor.capture()).onLoginFailed();

        checkLoginTask.checkLogin(email, password);

        verify(loginView).showLoginFail();

    }

    @Test
    public void inputCorrectEmailAndPassword_showLoginSuccess() {
        String email = "admin@yahoo.com";
        String password = "123456";
        loginPresenter.onLoginButtonClick(email, password);
        verify(loginView).gotoMainScreen(email);
    }
}
