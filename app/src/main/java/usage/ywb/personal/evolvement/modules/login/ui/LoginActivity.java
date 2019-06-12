package usage.ywb.personal.evolvement.modules.login.ui;

import android.os.Bundle;
import android.view.View;

import usage.ywb.personal.evolvement.R;
import usage.ywb.personal.evolvement.base.ui.BaseActivity;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.modules.login.presenter.LoginPresenter;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginPresenter> implements LoginContract.LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().login(null);
            }
        });
    }

    @Override
    protected LoginContract.LoginPresenter getPresenterInstance() {
        return new LoginPresenter(this);
    }

    @Override
    public void onLoginSucceed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showShortToast("登陆成功");
            }
        });
    }

    @Override
    public void onLoginFailure() {

    }


}
