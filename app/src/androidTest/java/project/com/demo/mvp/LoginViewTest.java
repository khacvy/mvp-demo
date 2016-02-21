package project.com.demo.mvp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.com.demo.mvp.login.view.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Khắc Vỹ on 2/22/2016.
 */
@RunWith(AndroidJUnit4.class)
public class LoginViewTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void inputEmptyEmail_showError(){
        String email = "";
        String password = "123456";
        String error = loginActivityTestRule.getActivity().getString(R.string.error_missing_email);

        onView(withId(R.id.txtEmail)).perform(typeText(email));
        onView(withId(R.id.txtPassword)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(error)));
    }

}
