package usage.ywb.personal.evolvement.modules;

import android.os.Bundle;
import android.view.View;

import usage.ywb.personal.evolvement.R;
import usage.ywb.personal.evolvement.base.BaseActivity;
import usage.ywb.personal.evolvement.modules.login.LoginPresenter;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginActivity extends BaseActivity<Contract.LoginPresenter> implements Contract.LoginView {

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
    protected Contract.LoginPresenter getPresenterInstance() {
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
