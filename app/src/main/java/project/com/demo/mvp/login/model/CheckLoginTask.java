package project.com.demo.mvp.login.model;

import android.os.Handler;

/**
 * Created by Khắc Vỹ on 2/21/2016.
 */
public class CheckLoginTask {
    
    private CheckLoginListener listener;

    public CheckLoginTask(CheckLoginListener listener) {
        this.listener = listener;
    }

    /**
     * Check login
     * Use a handler and delayed 3s for testing
     *
     * @param email
     * @param password
     */
    public void checkLogin(final String email, final String password){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(email.equals("admin@yahoo.com") && password.equals("123456")){
                    listener.onLoginSuccess(email);
                }else{
                    listener.onLoginFailed();
                }
            }
        }, 3000);
    }

    public interface CheckLoginListener{
        void onLoginSuccess(String email);
        void onLoginFailed();
    }
}
