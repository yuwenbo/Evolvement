package usage.ywb.personal.evolvement.modules.login;

import android.os.SystemClock;

import usage.ywb.personal.evolvement.base.BaseModel;
import usage.ywb.personal.evolvement.base.IHttpError;
import usage.ywb.personal.evolvement.base.IResponseListener;
import usage.ywb.personal.evolvement.modules.Contract;
import usage.ywb.personal.evolvement.modules.User;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginModel extends BaseModel implements Contract.LoginModel, IHttpError {

    @Override
    public void login(final User user, final IResponseListener<Contract.LoginPresenter, User> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                listener.onResponseSucceed(null, user);
            }
        }).start();
    }


}
