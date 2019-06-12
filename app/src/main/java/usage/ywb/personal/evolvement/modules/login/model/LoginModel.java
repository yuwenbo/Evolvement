package usage.ywb.personal.evolvement.modules.login.model;

import android.os.SystemClock;

import usage.ywb.personal.evolvement.base.model.BaseModel;
import usage.ywb.personal.evolvement.base.nets.IResponseListener;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.entity.User;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginModel extends BaseModel implements LoginContract.LoginModel {

    @Override
    public void login(final User user, final IResponseListener<LoginContract.LoginPresenter, User> listener) {
        //处理网络请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                listener.onResponseSucceed(null, user);
            }
        }).start();
    }


}
