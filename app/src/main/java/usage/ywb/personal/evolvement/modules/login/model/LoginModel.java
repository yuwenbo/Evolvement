package usage.ywb.personal.evolvement.modules.login.model;

import android.os.SystemClock;

import usage.ywb.personal.evolvement.base.model.BaseModel;
import usage.ywb.personal.evolvement.entity.User;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.modules.login.presenter.LoginPresenter;

/**
 * Model层实现登录
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginModel extends BaseModel implements LoginContract.LoginModel<LoginPresenter> {

    @Override
    public void login(final User user, final LoginPresenter presenter) {
        //处理网络请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO
                SystemClock.sleep(1500);
                presenter.onLoginSucceed(user);
            }
        }).start();
    }


}
