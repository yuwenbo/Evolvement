package usage.ywb.personal.evolvement.modules.login.ui;

import android.os.Bundle;
import android.view.View;

import usage.ywb.personal.evolvement.R;
import usage.ywb.personal.evolvement.base.presenter.InjectPresenter;
import usage.ywb.personal.evolvement.base.ui.BaseActivity;
import usage.ywb.personal.evolvement.entity.User;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.modules.login.presenter.LoginPresenter;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginActivity extends BaseActivity implements LoginContract.LoginView<User> {

//    @InjectPresenter
    protected LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = getPresenter(LoginPresenter.class);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(null);
            }
        });
    }

    @Override
    public void onLoginSucceed(User user) {
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
