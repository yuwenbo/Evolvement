package usage.ywb.personal.evolvement.modules.login.presenter;

import android.support.annotation.NonNull;

import usage.ywb.personal.evolvement.base.presenter.BasePresenter;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.entity.User;
import usage.ywb.personal.evolvement.modules.login.model.LoginModel;

/**
 * 登录的Presenter，实现对Model的直接调用
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView<User>, LoginModel> implements LoginContract.LoginPresenter {

    @Override
    public void login(User user) {
        getView().showLoading("正在登录中...");
        getModel().login(user, this);
    }

    public void onLoginSucceed(User user) {
        getView().hideLoading();
        getView().onLoginSucceed(user);
    }

    void onLoginFailure() {
        getView().hideLoading();
        getView().showShortToast("登录失败");
        getView().onLoginFailure();
    }


}
